package com.labs2160.rxfiredroid.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.*
import com.labs2160.rxfiredroid.addRxOnCompleteListener
import io.reactivex.*
import io.reactivex.disposables.Disposable
import com.google.firebase.auth.FirebaseAuth as GoogleFirebaseAuth

@Suppress("unused")
internal class FirebaseAuthImpl(private val delegate: GoogleFirebaseAuth) : FirebaseAuth {
  override fun addAuthStateListener(listener: GoogleFirebaseAuth.AuthStateListener) {
    return delegate.addAuthStateListener(listener)
  }

  override fun addIdTokenListener(listener: GoogleFirebaseAuth.IdTokenListener) {
    return delegate.addIdTokenListener(listener)
  }

  override fun applyActionCode(code: String): Task<Void> {
    return delegate.applyActionCode(code)
  }

  override fun checkActionCode(code: String): Task<ActionCodeResult> {
    return delegate.checkActionCode(code)
  }

  override fun confirmPasswordReset(code: String, newPassword: String): Task<Void> {
    return delegate.confirmPasswordReset(code, newPassword)
  }

  override fun createUserWithEmailAndPassword(email: String, password: String): Task<AuthResult> {
    return delegate.createUserWithEmailAndPassword(email, password)
  }

  override fun fetchSignInMethodsForEmail(email: String): Task<SignInMethodQueryResult> {
    return delegate.fetchSignInMethodsForEmail(email)
  }

  override fun getApp(): FirebaseApp {
    return delegate.app
  }

  override fun getCurrentUser(): FirebaseUser? {
    return delegate.currentUser
  }

  override fun getFirebaseAuthSettings(): FirebaseAuthSettings {
    return delegate.firebaseAuthSettings
  }

  override fun getLanguageCode(): String? {
    return delegate.languageCode
  }

  override fun isSignInWithEmailLink(link: String): Boolean {
    return delegate.isSignInWithEmailLink(link)
  }

  override fun removeAuthStateListener(listener: GoogleFirebaseAuth.AuthStateListener) {
    delegate.removeAuthStateListener(listener)
  }

  override fun removeIdTokenListener(listener: GoogleFirebaseAuth.IdTokenListener) {
    delegate.removeIdTokenListener(listener)
  }

  override fun rxApplyActionCode(code: String): Completable {
    return Completable.create { emitter ->
      delegate.applyActionCode(code)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxApplyActionCode()")
    }
  }

  override fun rxBindAuthState(backpressureStrategy: BackpressureStrategy): Flowable<FirebaseAuth> {
    val source = FlowableOnSubscribe<FirebaseAuth> { emitter ->
      val listener = GoogleFirebaseAuth.AuthStateListener {
        emitter.onNext(this)
      }

      delegate.addAuthStateListener(listener)

      var unregistered = false

      emitter.setDisposable(object : Disposable {
        override fun isDisposed(): Boolean = unregistered

        override fun dispose() {
          unregistered = true

          delegate.removeAuthStateListener(listener)
        }
      })
    }

    return Flowable.create(source, backpressureStrategy)
  }

  override fun rxBindIdTokenState(backpressureStrategy: BackpressureStrategy): Flowable<FirebaseAuth> {
    val source = FlowableOnSubscribe<FirebaseAuth> { emitter ->
      val listener = GoogleFirebaseAuth.IdTokenListener {
        emitter.onNext(this)
      }

      delegate.addIdTokenListener(listener)

      var unregistered = false

      emitter.setDisposable(object : Disposable {
        override fun isDisposed(): Boolean = unregistered

        override fun dispose() {
          unregistered = true

          delegate.removeIdTokenListener(listener)
        }
      })
    }

    return Flowable.create(source, backpressureStrategy)
  }

  override fun rxCheckActionCode(code: String): Single<ActionCodeResult> {
    return Single.create { emitter ->
      delegate.checkActionCode(code)
          .addRxOnCompleteListener(emitter, "Firebase check action code failed")
    }
  }

  override fun rxConfirmPasswordReset(code: String, newPassword: String): Completable {
    return Completable.create { emitter ->
      delegate.confirmPasswordReset(code, newPassword)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxConfirmPasswordReset()")
    }
  }

  override fun rxCreateUserWithEmailAndPassword(email: String, password: String): Single<AuthResult> {
    return Single.create { emitter ->
      delegate.createUserWithEmailAndPassword(email, password)
          .addRxOnCompleteListener(emitter, "Firebase user creation with email and password failed")
    }
  }

  override fun rxFetchSignInMethodsForEmail(email: String): Single<SignInMethodQueryResult> {
    return Single.create { emitter ->
      delegate.fetchSignInMethodsForEmail(email)
          .addRxOnCompleteListener(emitter, "Firebase fetch sign in methods for email failed")
    }
  }

  override fun rxSendPasswordResetEmail(email: String, actionCodeSettings: ActionCodeSettings): Completable {
    return Completable.create { emitter ->
      delegate.sendPasswordResetEmail(email, actionCodeSettings)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxSendPasswordResetEmail()")
    }
  }

  override fun rxSendPasswordResetEmail(email: String): Completable {
    return Completable.create { emitter ->
      delegate.sendPasswordResetEmail(email)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxSendPasswordResetEmail()")
    }
  }

  override fun rxSendSignInLinkToEmail(email: String, actionCodeSettings: ActionCodeSettings): Completable {
    return Completable.create { emitter ->
      delegate.sendSignInLinkToEmail(email, actionCodeSettings)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxSendSignInLinkToEmail()")
    }
  }

  override fun rxSignInAnonymously(): Single<AuthResult> {
    return Single.create { emitter ->
      delegate.signInAnonymously()
          .addRxOnCompleteListener(emitter, "Firebase anonymous sign-in failed")
    }
  }

  override fun rxSignInWithCredential(credential: AuthCredential): Single<AuthResult> {
    return Single.create { emitter ->
      delegate.signInWithCredential(credential)
          .addRxOnCompleteListener(emitter, "Firebase sign-in with federated identity credentials failed")
    }
  }

  override fun rxSignInWithCustomToken(token: String): Single<AuthResult> {
    return Single.create { emitter ->
      delegate.signInWithCustomToken(token)
          .addRxOnCompleteListener(emitter, "Firebase sign-in with custom token failed")
    }
  }

  override fun rxSignInWithEmailAndPassword(email: String, password: String): Single<AuthResult> {
    return Single.create { emitter ->
      delegate.signInWithEmailAndPassword(email, password)
          .addRxOnCompleteListener(emitter, "Firebase sign-in with email and password credentials failed")
    }
  }

  override fun rxSignInWithEmailLink(email: String, link: String): Single<AuthResult> {
    return Single.create { emitter ->
      delegate.signInWithEmailLink(email, link)
          .addRxOnCompleteListener(emitter, "Firebase sign-in with email link failed")
    }
  }

  override fun rxUpdateCurrentUser(user: FirebaseUser): Completable {
    return Completable.create { emitter ->
      delegate.updateCurrentUser(user)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxUpdateCurrentUser()")
    }
  }

  override fun rxVerifyPasswordResetCode(code: String): Single<String> {
    return Single.create { emitter ->
      delegate.verifyPasswordResetCode(code)
          .addRxOnCompleteListener(emitter, "Firebase password reset code verification failed")
    }
  }

  override fun sendPasswordResetEmail(email: String, actionCodeSettings: ActionCodeSettings): Task<Void> {
    return delegate.sendPasswordResetEmail(email, actionCodeSettings)
  }

  override fun sendPasswordResetEmail(email: String): Task<Void> {
    return delegate.sendPasswordResetEmail(email)
  }

  override fun sendSignInLinkToEmail(email: String, actionCodeSettings: ActionCodeSettings): Task<Void> {
    return delegate.sendSignInLinkToEmail(email, actionCodeSettings)
  }

  override fun setLanguageCode(languageCode: String) {
    return delegate.setLanguageCode(languageCode)
  }

  override fun signInAnonymously(): Task<AuthResult> {
    return delegate.signInAnonymously()
  }

  override fun signInWithCredential(credential: AuthCredential): Task<AuthResult> {
    return delegate.signInWithCredential(credential)
  }

  override fun signInWithCustomToken(token: String): Task<AuthResult> {
    return delegate.signInWithCustomToken(token)
  }

  override fun signInWithEmailAndPassword(email: String, password: String): Task<AuthResult> {
    return delegate.signInWithEmailAndPassword(email, password)
  }

  override fun signInWithEmailLink(email: String, link: String): Task<AuthResult> {
    return delegate.signInWithEmailLink(email, link)
  }

  override fun signOut() {
    return delegate.signOut()
  }

  override fun updateCurrentUser(user: FirebaseUser): Task<Void> {
    return delegate.updateCurrentUser(user)
  }

  override fun useAppLanguage() {
    return delegate.useAppLanguage()
  }

  override fun verifyPasswordResetCode(code: String): Task<String> {
    return delegate.verifyPasswordResetCode(code)
  }
}