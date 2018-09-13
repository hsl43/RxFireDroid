package com.labs2160.rxfiredroid.samples.kotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.FirebaseApp
import com.labs2160.rxfiredroid.auth.FirebaseAuthAppCompatActivity
import com.labs2160.rxfiredroid.auth.GitHubAuthConfiguration
import com.labs2160.rxfiredroid.auth.GoogleAuthConfiguration
import com.labs2160.rxfiredroid.auth.TwitterAuthConfiguration
import com.labs2160.rxfiredroid.samples.R
import com.twitter.sdk.android.core.identity.TwitterLoginButton
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.firebase_auth_demo_activity.*

class FirebaseAuthSampleActivity : FirebaseAuthAppCompatActivity() {
  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.firebase_auth_demo_activity)

    email_password_sign_in_button.setOnClickListener {
      val email    = "..."
      val password = "..."

      disposables.add(
          firebaseAuth.rxSignInWithEmailAndPassword(email, password)
              .subscribeBy(
                  onSuccess = { _ ->
                    Log.d(javaClass.name, "## Sign-in to Firebase with email and password succeeded")

                    disposables.clear()
                  },
                  onError = { error ->
                    Log.e(javaClass.name, "## Sign-in to Firebase with email and password failed", error)
                  }
              )
      )
    }

    disposables.addAll(
        bindGitHubAuth().subscribeBy(
            onSuccess = { _ ->
              Log.d(javaClass.name, "## Sign-in to Firebase with GitHub credentials succeeded")

              disposables.clear()
            },
            onError = { error ->
              Log.e(javaClass.name, "## Sign-in to Firebase with GitHub credentials failed", error)
            }
        ),
        bindGoogleAuth().subscribeBy(
            onSuccess = { _ ->
              Log.d(javaClass.name, "## Sign-in to Firebase with Google credentials succeeded")

              disposables.clear()
            },
            onError = { error ->
              Log.e(javaClass.name, "## Sign-in to Firebase with Google credentials failed", error)
            }
        ),
        bindTwitterAuth().subscribeBy(
            onSuccess = { _ ->
              Log.d(javaClass.name, "## Sign-in to Firebase with Twitter credentials succeeded")

              disposables.clear()
            },
            onError = { error ->
              Log.e(javaClass.name, "## Sign-in to Firebase with Twitter credentials failed", error)
            }
        )
    )

    sign_out_button.setOnClickListener { firebaseAuth.signOut() }

    FirebaseApp.initializeApp(this)
  }

  override fun onDestroy() {
    super.onDestroy()

    disposables.clear()
  }

  override fun gitHubAuthConfiguration(): GitHubAuthConfiguration? {
    return GitHubAuthConfiguration(
        clientId     = "...",
        clientSecret = "...",
        callbackUrl  = "...",
        scope        = null
    )
  }

  override fun gitHubSignInButton(): View? = git_hub_sign_in_button

  override fun googleAuthConfiguration(): GoogleAuthConfiguration? {
    return GoogleAuthConfiguration(
        requestIdToken = "..."
    )
  }

  override fun googleSignInButton(): View = google_sign_in_button

  override fun twitterAuthConfiguration(): TwitterAuthConfiguration? {
    return TwitterAuthConfiguration(
        consumerKey    = "...",
        consumerSecret = "...",
        debug          = true,
        logLevel       = Log.DEBUG
    )
  }

  override fun twitterLoginButton(): TwitterLoginButton = twitter_sign_in_button
}