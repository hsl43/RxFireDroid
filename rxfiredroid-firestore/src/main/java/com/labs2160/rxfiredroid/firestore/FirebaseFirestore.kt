package com.labs2160.rxfiredroid.firestore

import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestoreSettings
import io.reactivex.Completable
import io.reactivex.Single
import com.google.firebase.firestore.CollectionReference as GoogleCollectionReference
import com.google.firebase.firestore.DocumentReference as GoogleDocumentReference
import com.google.firebase.firestore.FirebaseFirestore as GoogleFirebaseFirestore
import com.google.firebase.firestore.Transaction as GoogleTransaction
import com.google.firebase.firestore.WriteBatch as GoogleWriteBatch

interface FirebaseFirestore {
  companion object {
    private val lock = Any()

    private var instance: FirebaseFirestoreImpl? = null

    fun getInstance(): FirebaseFirestore {
      return FirebaseFirestoreImpl(GoogleFirebaseFirestore.getInstance())
    }

    fun getInstance(app: FirebaseApp): FirebaseFirestore {
      return FirebaseFirestoreImpl(GoogleFirebaseFirestore.getInstance(app))
    }

    fun getInstance(googleFirebaseFirestore: GoogleFirebaseFirestore): FirebaseFirestore {
      synchronized(lock) {
        if(instance == null) {
          instance = FirebaseFirestoreImpl(googleFirebaseFirestore)
        }
      }

      return instance!!
    }

    /**
     * Globally enables / disables Firestore logging for the SDK.
     */
    @Suppress("unused")
    fun setLoggingEnabled(loggingEnabled: Boolean) {
      setLoggingEnabled(loggingEnabled)
    }
  }
  /**
   * Creates a write batch, used for performing multiple writes as a single
   * atomic operation.
   */
  fun batch(): GoogleWriteBatch

  /**
   * Gets a CollectionReference instance that refers to the collection at the
   * specified [path] within the database.
   */
  fun collection(path: String): GoogleCollectionReference

  /**
   * Disables network access for this instance.
   */
  fun disableNetwork(): Task<Void>

  /**
   * Gets a `DocumentReference` instance that refers to the document at the
   * specified [path] within the database.
   */
  fun document(path: String): GoogleDocumentReference

  /**
   * Re-enables network usage for this instance after a prior call to
   * disableNetwork().
   */
  fun enableNetwork(): Task<Void>

  /**
   * Returns the FirebaseApp instance to which this FirebaseFirestore belongs.
   */
  fun getApp(): FirebaseApp

  /**
   * Returns the settings used by this FirebaseFirestore object.
   */
  fun getFirestoreSettings(): FirebaseFirestoreSettings

  /**
   * Executes the given [updateFunction] and then attempts to commit the
   * changes applied within the transaction.
   */
  fun <T> runTransaction(updateFunction: GoogleTransaction.Function<T>): Task<T>

  /**
   * Creates a write batch, used for performing multiple writes as a single
   * atomic operation.
   */
  fun rxBatch(): WriteBatch

  /**
   * Gets a CollectionReference instance that refers to the collection at the
   * specified [path] within the database.
   */
  fun rxCollection(path: String): CollectionReference

  /**
   * Gets a `DocumentReference` instance that refers to the document at the
   * specified [path] within the database.
   */
  fun rxDocument(path: String): DocumentReference

  /**
   * Disables network access for this instance.
   */
  fun rxDisableNetwork(): Completable

  /**
   * Re-enables network usage for this instance after a prior call to
   * disableNetwork().
   */
  fun rxEnableNetwork(): Completable

  /**
   * Executes the given [updateFunction] and then attempts to commit the
   * changes applied within the transaction.
   */
  fun <T> rxRunTransaction(updateFunction: (Transaction) -> T): Single<T>

  /**
   * Sets any custom [settings] used to configure this FirebaseFirestore
   * object.
   */
  fun setFirestoreSettings(settings: FirebaseFirestoreSettings)
}