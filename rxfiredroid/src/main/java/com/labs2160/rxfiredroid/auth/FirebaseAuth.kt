package com.labs2160.rxfiredroid.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.*
import com.labs2160.rxfiredroid.Defaults
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import com.google.firebase.auth.FirebaseAuth as GoogleFirebaseAuth

interface FirebaseAuth {
  companion object {
    private val lock = Any()

    private var instance: FirebaseAuthImpl? = null

    fun getInstance(): FirebaseAuth {
      return FirebaseAuthImpl(GoogleFirebaseAuth.getInstance())
    }

    fun getInstance(googleFirebaseAuth: GoogleFirebaseAuth): FirebaseAuth {
      synchronized(lock) {
        if(instance == null) {
          instance = FirebaseAuthImpl(googleFirebaseAuth)
        }
      }

      return instance!!
    }
  }

  /**
   * Registers a [listener] to changes in the user authentication state.
   */
  fun addAuthStateListener(listener: GoogleFirebaseAuth.AuthStateListener)

  /**
   * Registers a [listener] to changes in the token authentication state.
   */
  fun addIdTokenListener(listener: GoogleFirebaseAuth.IdTokenListener)

  /**
   * Applies the given [code], which can be any out of band code which is valid
   * according to checkActionCode(String) that does not also pass
   * verifyPasswordResetCode(String), which requires an additional parameter.
   */
  fun applyActionCode(code: String): Task<Void>

  /**
   * Checks that the [code] given is valid.
   */
  fun checkActionCode(code: String): Task<ActionCodeResult>

  /**
   * Changes the user's password to [newPassword] for the account for which the
   * [code] is valid.
   */
  fun confirmPasswordReset(code: String, newPassword: String): Task<Void>

  /**
   * Tries to create a new user account with the given [email] address and
   * [password].
   */
  fun createUserWithEmailAndPassword(email: String, password: String): Task<AuthResult>

  /**
   * Returns a list of signin methods that can be used to sign in a given user
   * (identified by its main [email] address).
   */
  fun fetchSignInMethodsForEmail(email: String): Task<SignInMethodQueryResult>

  /**
   * Returns the FirebaseApp instance to which this FirebaseAuth belongs.
   */
  fun getApp(): FirebaseApp

  /**
   * Returns the currently signed-in FirebaseUser or null if there is none.
   */
  fun getCurrentUser(): FirebaseUser?

  /**
   * Returns the FirebaseAuthSettings instance for this FirebaseAuth instance.
   */
  fun getFirebaseAuthSettings(): FirebaseAuthSettings

  /**
   * Returns the language code set in setLanguageCode(String).
   */
  fun getLanguageCode(): String?

  /**
   * Determines if the given [link] is a link intended for use with
   * getCredentialWithLink(String, String).
   */
  fun isSignInWithEmailLink(link: String): Boolean

  /**
   * Unregisters a [listener] to authentication changes.
   */
  fun removeAuthStateListener(listener: GoogleFirebaseAuth.AuthStateListener)

  /**
   * Unregisters a [listener] to authentication changes.
   */
  fun removeIdTokenListener(listener: GoogleFirebaseAuth.IdTokenListener)

  /**
   * Applies the given [code], which can be any out of band code which is valid
   * according to checkActionCode(String) that does not also pass
   * verifyPasswordResetCode(String), which requires an additional parameter.
   */
  fun rxApplyActionCode(code: String): Completable

  /**
   * Observe changes in the user authentication state with a defaulted
   * backpressure strategy.
   */
  fun rxBindAuthState(): Flowable<FirebaseAuth> {
    return this.rxBindAuthState(Defaults.BACKPRESSURE_STRATEGY)
  }

  /**
   * Observe changes in the user authentication state.
   */
  fun rxBindAuthState(backpressureStrategy: BackpressureStrategy): Flowable<FirebaseAuth>

  /**
   * Observe changes in the token authentication state with a defaulted
   * backpressure strategy.
   */
  fun rxBindIdTokenState(): Flowable<FirebaseAuth> {
    return this.rxBindIdTokenState(Defaults.BACKPRESSURE_STRATEGY)
  }

  /**
   * Observe changes in the token authentication state.
   */
  fun rxBindIdTokenState(backpressureStrategy: BackpressureStrategy): Flowable<FirebaseAuth>

  /**
   * Checks that the [code] given is valid.
   */
  fun rxCheckActionCode(code: String): Single<ActionCodeResult>

  /**
   * Changes the user's password to [newPassword] for the account for which
   * the [code] is valid.
   */
  fun rxConfirmPasswordReset(code: String, newPassword: String): Completable

  /**
   * Tries to create a new user account with the given [email] address and
   * [password].
   */
  fun rxCreateUserWithEmailAndPassword(email: String, password: String): Single<AuthResult>

  /**
   * Returns a list of sign-in methods that can be used to sign in a given user
   * (identified by its main [email] address).
   */
  fun rxFetchSignInMethodsForEmail(email: String): Single<SignInMethodQueryResult>

  /**
   * Triggers the Firebase Authentication backend to send a password-reset
   * email to the given [email] address, which must correspond to an existing
   * user of your app.
   */
  fun rxSendPasswordResetEmail(email: String, actionCodeSettings: ActionCodeSettings): Completable

  /**
   * Calls rxSendPasswordResetEmail(String, ActionCodeSettings) without any
   * ActionCodeSettings.
   */
  fun rxSendPasswordResetEmail(email: String): Completable

  /**
   * Sends an email to the specified [email] which will contain a link to be used to sign in the user.
   */
  fun rxSendSignInLinkToEmail(email: String, actionCodeSettings: ActionCodeSettings): Completable

  /**
   * Signs in the user anonymously without requiring any credential.
   */
  fun rxSignInAnonymously(): Single<AuthResult>

  /**
   * Tries to sign in a user with the given [credential].
   */
  fun rxSignInWithCredential(credential: AuthCredential): Single<AuthResult>

  /**
   * Tries to sign in a user with a given custom [token].
   */
  fun rxSignInWithCustomToken(token: String): Single<AuthResult>

  /**
   * Tries to sign in a user with the given [email] address and [password].
   */
  fun rxSignInWithEmailAndPassword(email: String, password: String): Single<AuthResult>

  /**
   * Tries to sign in a user with the given [email] address and [link].
   */
  fun rxSignInWithEmailLink(email: String, link: String): Single<AuthResult>

  /**
   * Sets the current user to a copy of the given [user], but associated with this FirebaseAuth's FirebaseApp.
   */
  fun rxUpdateCurrentUser(user: FirebaseUser): Completable

  /**
   * Checks that the [code] is a valid password reset out of band code.
   */
  fun rxVerifyPasswordResetCode(code: String): Single<String>

  /**
   * Triggers the Firebase Authentication backend to send a password-reset
   * email to the given [email] address, which must correspond to an existing
   * user of your app.
   */
  fun sendPasswordResetEmail(email: String, actionCodeSettings: ActionCodeSettings): Task<Void>

  /**
   * Calls sendPasswordResetEmail(String, ActionCodeSettings) without any ActionCodeSettings.
   */
  fun sendPasswordResetEmail(email: String): Task<Void>

  /**
   * Sends an email to the specified [email] which will contain a link to be
   * used to sign in the user.
   */
  fun sendSignInLinkToEmail(email: String, actionCodeSettings: ActionCodeSettings): Task<Void>

  /**
   * Sets the user-facing language code for auth operations that can be
   * internationalized, such as sendEmailVerification().
   */
  fun setLanguageCode(languageCode: String)

  /**
   * Signs in the user anonymously without requiring any credential.
   */
  fun signInAnonymously(): Task<AuthResult>

  /**
   * Tries to sign in a user with the given AuthCredential.
   */
  fun signInWithCredential(credential: AuthCredential): Task<AuthResult>

  /**
   * Tries to sign in a user with a given custom [token].
   */
  fun signInWithCustomToken(token: String): Task<AuthResult>

  /**
   * Tries to sign in a user with the given [email] address and [password].
   */
  fun signInWithEmailAndPassword(email: String, password: String): Task<AuthResult>

  /**
   * Tries to sign in a user with the given email address and link.
   */
  fun signInWithEmailLink(email: String, link: String): Task<AuthResult>

  /**
   * Signs out the current user and clears it from the disk cache.
   */
  fun signOut()

  /**
   * Sets the current user to a copy of the given [user], but associated with
   * this FirebaseAuth's FirebaseApp.
   */
  fun updateCurrentUser(user: FirebaseUser): Task<Void>

  /**
   * Sets the user-facing language code to be the default app language.
   */
  fun useAppLanguage()

  /**
   * Checks that the [code] is a valid password reset out of band code.
   */
  fun verifyPasswordResetCode(code: String): Task<String>
}