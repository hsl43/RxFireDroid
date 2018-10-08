package com.labs2160.rxfiredroid.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterLoginButton
import io.reactivex.Single
import java.util.*
import com.google.firebase.auth.FirebaseAuth as GoogleFirebaseAuth
import com.twitter.sdk.android.core.Callback as TwitterCallback

abstract class FirebaseAuthAppCompatActivity : AppCompatActivity() {
  protected lateinit var firebaseAuth: FirebaseAuth

  private var registerCallbacks = true

  companion object {
    private const val GOOGLE_SIGN_IN_REQUEST_CODE = 38863
    private const val TWITTER_LOGIN_REQUEST_CODE  = TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    firebaseAuth = FirebaseAuth.getInstance()

    gitHubAuthConfiguration()?.let { configuration ->
      intent?.data?.let { uri ->
        val results = uri.toString()
            .replace("${configuration.callbackUrl}?", "")
            .split("&")
            .associate { keyValue -> with(keyValue.split("=")) { this[0] to this[1] } }

        val code = results["code"] ?: throw IllegalArgumentException("Code not supplied in response to GitHub identity request")

        val state = results["state"] ?: throw IllegalArgumentException("State not supplied in response to GitHub identity request")

        if(state != Streams.gitHubState()) {
          throw IllegalArgumentException("State in GitHub identity response does not match state from request")
        }

        Streams.accept(GitHubTempToken(code, state))
      }
    }

    twitterAuthConfiguration()?.let {
      if(registerCallbacks) {
        initializeTwitterApi(it)
      }
    }
  }

  override fun onStart() {
    super.onStart()

    if(registerCallbacks) {
      gitHubAuthConfiguration()?.let { configuration ->
        val button = gitHubSignInButton()

        if(button == null) {
          throw IllegalStateException("GitHub auth configuration was supplied but View for sign-in button was not")
        } else {
          button.setOnClickListener {
            val state = UUID.randomUUID().toString()

            val url = StringBuilder("https://github.com/login/oauth/authorize")
                .append("?client_id=").append(configuration.clientId)
                .append("&redirect_uri=").append(configuration.callbackUrl)
                .append("&state=").append(state)

            if(configuration.scope != null) {
              url.append("&scope=").append(configuration.scope)
            }

            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url.toString())))

            Streams.gitHubState(state)
          }
        }
      }

      googleAuthConfiguration()?.let { configuration ->
        val button = googleSignInButton()

        if(button == null) {
          throw IllegalStateException("Google auth configuration was supplied but View for sign-in button was not")
        } else {
          button.setOnClickListener {
            val optionsBuilder = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(configuration.requestIdToken)

            if(configuration.requestId) {
              optionsBuilder.requestId()
            }

            if(configuration.requestEmail) {
              optionsBuilder.requestEmail()
            }

            if(configuration.requestProfile) {
              optionsBuilder.requestProfile()
            }

            val googleSignInOptions = optionsBuilder.build()

            val googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

            startActivityForResult(googleSignInClient.signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE)
          }
        }
      }

      twitterAuthConfiguration()?.let {
        val button = twitterLoginButton()

        if(button == null) {
          throw IllegalStateException("Twitter auth configuration was supplied but View for login button was not")
        } else {
          button.callback = object : TwitterCallback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
              Streams.accept(TwitterAuthResult(authToken = result.data.authToken))
            }

            override fun failure(exception: TwitterException) {
              Streams.accept(TwitterAuthResult(error = exception))
            }
          }
        }
      }

      registerCallbacks = false
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    when(requestCode) {
      GOOGLE_SIGN_IN_REQUEST_CODE -> {
        val googleSignInTask = GoogleSignIn.getSignedInAccountFromIntent(data)

        if(googleSignInTask.isSuccessful) {
          val account = googleSignInTask.result

          if(account == null) {
            Streams.accept(GoogleAuthResult(error = RuntimeException("Google sign-in task completed but no account was resolved")))
          } else {
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)

            Streams.accept(GoogleAuthResult(credential))
          }

        } else {
          Streams.accept(GoogleAuthResult(error = googleSignInTask.exception))
        }
      }

      TWITTER_LOGIN_REQUEST_CODE -> {
        twitterLoginButton()?.onActivityResult(requestCode, resultCode, data)
      }
    }
  }

  fun bindGitHubAuth(force: Boolean = false): Single<AuthResult> {
    val configuration = gitHubAuthConfiguration() ?: throw IllegalStateException("Google auth configuration was not supplied")

    return Streams.bindGitHubAuth(firebaseAuth, configuration, force)
  }

  fun bindGoogleAuth(force: Boolean = false): Single<AuthResult> = Streams.bindGoogleAuth(firebaseAuth, force)

  fun bindTwitterAuth(force: Boolean = false): Single<AuthResult> = Streams.bindTwitterAuth(firebaseAuth, force)

  protected open fun gitHubAuthConfiguration(): GitHubAuthConfiguration? = null

  protected open fun gitHubSignInButton(): View? = null

  protected open fun googleAuthConfiguration(): GoogleAuthConfiguration? = null

  protected open fun googleSignInButton(): View? = null

  protected open fun twitterAuthConfiguration(): TwitterAuthConfiguration? = null

  protected open fun twitterLoginButton(): TwitterLoginButton? = null

  private fun initializeTwitterApi(configuration: TwitterAuthConfiguration) {
    Twitter.initialize(TwitterConfig.Builder(this)
        .logger(DefaultLogger(configuration.logLevel))
        .twitterAuthConfig(TwitterAuthConfig(configuration.consumerKey, configuration.consumerSecret))
        .debug(configuration.debug)
        .build()
    )
  }
}