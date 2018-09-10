package com.labs2160.rxfiredroid.firestore

import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.labs2160.rxfiredroid.addRxOnCompleteListener
import io.reactivex.Completable
import io.reactivex.Single
import com.google.firebase.firestore.CollectionReference as GoogleCollectionReference
import com.google.firebase.firestore.DocumentReference as GoogleDocumentReference
import com.google.firebase.firestore.FirebaseFirestore as GoogleFirebaseFirestore
import com.google.firebase.firestore.Transaction as GoogleTransaction
import com.google.firebase.firestore.WriteBatch as GoogleWriteBatch

@Suppress("unused")
internal class FirebaseFirestoreImpl(private val delegate: GoogleFirebaseFirestore) : FirebaseFirestore {
  override fun batch(): GoogleWriteBatch {
    return delegate.batch()
  }

  override fun collection(path: String): GoogleCollectionReference {
    return delegate.collection(path)
  }

  override fun disableNetwork(): Task<Void> {
    return delegate.disableNetwork()
  }

  override fun document(path: String): GoogleDocumentReference {
    return delegate.document(path)
  }

  override fun enableNetwork(): Task<Void> {
    return delegate.enableNetwork()
  }

  override fun getApp(): FirebaseApp {
    return delegate.app
  }

  override fun getFirestoreSettings(): FirebaseFirestoreSettings {
    return delegate.firestoreSettings
  }

  override fun <T> runTransaction(updateFunction: GoogleTransaction.Function<T>): Task<T> {
    return delegate.runTransaction(updateFunction)
  }

  override fun rxBatch(): WriteBatch {
    return WriteBatch.newInstance(delegate.batch())
  }

  override fun rxCollection(path: String): CollectionReference {
    return QueryableCollectionReference.newInstance(delegate.collection(path))
  }

  override fun rxDocument(path: String): DocumentReference {
    return DocumentReference.newInstance(delegate.document(path))
  }
  override fun rxDisableNetwork(): Completable {
    return Completable.create { emitter ->
      delegate.disableNetwork()
          .addRxOnCompleteListener(emitter, "Unexpected error in rxDisableNetwork()")
    }
  }

  override fun rxEnableNetwork(): Completable {
    return Completable.create { emitter ->
      delegate.enableNetwork()
          .addRxOnCompleteListener(emitter, "Unexpected error in rxEnableNetwork()")
    }
  }

  override fun <T> rxRunTransaction(updateFunction: (Transaction) -> T): Single<T> {
    return Single.create { emitter ->
      delegate.runTransaction { transaction ->
        updateFunction(Transaction.newInstance(transaction))
      }
          .addRxOnCompleteListener(emitter, "Unexpected error in rxRunTransaction()")
    }
  }

  override fun setFirestoreSettings(settings: FirebaseFirestoreSettings) {
    delegate.firestoreSettings = settings
  }
}