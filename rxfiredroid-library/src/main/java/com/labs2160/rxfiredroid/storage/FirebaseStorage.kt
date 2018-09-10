package com.labs2160.rxfiredroid.storage

import com.google.firebase.FirebaseApp
import com.google.firebase.storage.FirebaseStorage as GoogleFirebaseStorage
import com.google.firebase.storage.StorageReference as GoogleStorageReference

interface FirebaseStorage {
  companion object {
    private val lock = Any()

    private var instance: FirebaseStorageImpl? = null

    /**
     * Returns the FirebaseStorage, initialized with the default FirebaseApp.
     */
    fun getInstance(): FirebaseStorage {
      return FirebaseStorage.getInstance(app = null, url = null)
    }

    /**
     * Returns the FirebaseStorage, initialized with a custom FirebaseApp
     */
    fun getInstance(app: FirebaseApp): FirebaseStorage {
      return FirebaseStorage.getInstance(app = app, url = null)
    }

    /**
     * Returns the FirebaseStorage, initialized with the default FirebaseApp
     * and a custom Storage Bucket.
     */
    fun getInstance(url: String): FirebaseStorage {
      return FirebaseStorage.getInstance(app = null, url = url)
    }

    /**
     * Returns the FirebaseStorage, initialized with a custom FirebaseApp and a
     * custom Storage Bucket.
     */
    fun getInstance(app: FirebaseApp? = null, url: String? = null): FirebaseStorage {
      synchronized(lock) {
        val delegate = when {
          app != null && url != null -> GoogleFirebaseStorage.getInstance(app, url)
          app != null && url == null -> GoogleFirebaseStorage.getInstance(app)
          app == null && url != null -> GoogleFirebaseStorage.getInstance(url)
                                else -> GoogleFirebaseStorage.getInstance()
        }

        instance = FirebaseStorageImpl(delegate)
      }

      return instance!!
    }
  }

  /**
   * The FirebaseApp associated with this FirebaseStorage instance.
   */
  fun getApp(): FirebaseApp

  /**
   * Returns the maximum time to retry a download if a failure occurs.
   */
  fun getMaxDownloadRetryTimeMillis(): Long

  /**
   * Returns the maximum time to retry operations other than upload and
   * download if a failure occurs.
   */
  fun getMaxOperationRetryTimeMillis(): Long

  /**
   * Returns the maximum time to retry an upload if a failure occurs.
   */
  fun getMaxUploadRetryTimeMillis(): Long

  /**
   * Creates a new StorageReference initialized at the root Firebase Storage
   * location.
   */
  fun getReference(): GoogleStorageReference

  /**
   * Creates a new StorageReference initialized with a child Firebase Storage
   * location.
   */
  fun getReference(location: String): GoogleStorageReference

  /**
   * Creates a StorageReference given a gs:// or // URL pointing to a Firebase
   * Storage location.
   */
  fun getReferenceFromUrl(fullUrl: String): GoogleStorageReference

  /**
   * Creates a new StorageReference initialized at the root Firebase Storage
   * location.
   */
  fun rxGetReference(): StorageReference

  /**
   * Creates a new StorageReference initialized with a child Firebase Storage
   * location.
   */
  fun rxGetReference(location: String): StorageReference

  /**
   * Creates a StorageReference given a gs:// or // URL pointing to a Firebase
   * Storage location.
   */
  fun rxGetReferenceFromUrl(fullUrl: String): StorageReference

  /**
   * Sets the maximum time to retry a download if a failure occurs.
   */
  fun setMaxDownloadRetryTimeMillis(maxTransferRetryMillis: Long)

  /**
   * Sets the maximum time to retry operations other than upload and download
   * if a failure occurs.
   */
  fun setMaxOperationRetryTimeMillis(maxTransferRetryMillis: Long)

  /**
   * Sets the maximum time to retry an upload if a failure occurs.
   */
  fun setMaxUploadRetryTimeMillis(maxTransferRetryMillis: Long)
}