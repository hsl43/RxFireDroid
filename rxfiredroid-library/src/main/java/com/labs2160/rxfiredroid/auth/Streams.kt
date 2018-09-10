package com.labs2160.rxfiredroid.auth

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GithubAuthProvider
import com.google.firebase.auth.TwitterAuthProvider
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

internal object Streams {
  private val lock = Any()

  private var gitHubRandomState = ""

  private val gitHubTempTokenChannel by lazy { PublishSubject.create<GitHubTempToken>() }
  private val googleAuthResultChannel by lazy { PublishSubject.create<GoogleAuthResult>() }
  private val twitterAuthResultChannel by lazy { PublishSubject.create<TwitterAuthResult>() }

  private var gitHubAuthSingle: Single<AuthResult>? = null
  private var googleAuthSingle: Single<AuthResult>? = null
  private var twitterAuthSingle: Single<AuthResult>? = null

  fun accept(token: GitHubTempToken) = gitHubTempTokenChannel.onNext(token)

  fun accept(result: GoogleAuthResult) = googleAuthResultChannel.onNext(result)

  fun accept(result: TwitterAuthResult) = twitterAuthResultChannel.onNext(result)

  fun bindGitHubAuth(
      firebaseAuth: FirebaseAuth,
      configuration: GitHubAuthConfiguration,
      force: Boolean = false

  ): Single<AuthResult> {

    synchronized(lock) {
      if(force || gitHubAuthSingle == null) {
        gitHubAuthSingle =
            gitHubTempTokenChannel
                .observeOn(Schedulers.io())
                .flatMap { tempToken ->
                  Observable.fromCallable(GetGitHubAccessToken(configuration, tempToken))
                }
                .flatMapSingle { state ->
                  if(state.token != null) {
                    val credential = GithubAuthProvider.getCredential(state.token.accessToken)

                    firebaseAuth.rxSignInWithCredential(credential)

                  } else {
                    if(state.errorCause != null) {
                      Single.error(state.errorCause)
                    } else {
                      val message = state.errorMessage ?: "Unexpected error during request for access token"

                      Single.error(RuntimeException(message))
                    }
                  }
                }
                .firstOrError()
                .cache()
      }
    }

    return gitHubAuthSingle!!
  }

  fun bindGoogleAuth(firebaseAuth: FirebaseAuth, force: Boolean = false): Single<AuthResult> {
    synchronized(lock) {
      if(force || googleAuthSingle == null) {
        googleAuthSingle =
            googleAuthResultChannel
                .flatMapSingle {
                  if(it.credential != null) {
                    firebaseAuth.rxSignInWithCredential(it.credential)
                  } else {
                    Single.error(it.error)
                  }
                }
                .firstOrError()
                .cache()
      }
    }

    return googleAuthSingle!!
  }

  fun bindTwitterAuth(firebaseAuth: FirebaseAuth, force: Boolean = false): Single<AuthResult> {
    synchronized(lock) {
      if(force || twitterAuthSingle == null) {
        twitterAuthSingle =
            twitterAuthResultChannel
                .flatMap {
                  if(it.authToken != null) {
                    Observable.just(TwitterAuthProvider.getCredential(it.authToken.token, it.authToken.secret))
                  } else {
                    Observable.error(it.error ?: RuntimeException("Unexpected error while resolving Twitter auth token"))
                  }
                }
                .flatMapSingle {
                  firebaseAuth.rxSignInWithCredential(it)
                }
                .firstOrError()
                .cache()
      }
    }

    return twitterAuthSingle!!
  }

  fun gitHubState(): String = gitHubRandomState

  fun gitHubState(state: String) {
    gitHubRandomState = state
  }
}