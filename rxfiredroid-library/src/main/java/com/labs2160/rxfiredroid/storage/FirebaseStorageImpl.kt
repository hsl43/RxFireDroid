package com.labs2160.rxfiredroid.storage

import com.google.firebase.FirebaseApp
import com.google.firebase.storage.FirebaseStorage as GoogleFirebaseStorage
import com.google.firebase.storage.StorageReference as GoogleStorageReference

@Suppress("unused")
internal class FirebaseStorageImpl(private val delegate: GoogleFirebaseStorage) : FirebaseStorage {
  override fun getApp(): FirebaseApp {
    return delegate.app
  }

  override fun getMaxDownloadRetryTimeMillis(): Long {
    return delegate.maxDownloadRetryTimeMillis
  }

  override fun getMaxOperationRetryTimeMillis(): Long {
    return delegate.maxOperationRetryTimeMillis
  }

  override fun getMaxUploadRetryTimeMillis(): Long {
    return delegate.maxUploadRetryTimeMillis
  }

  override fun getReference(): GoogleStorageReference {
    return delegate.reference
  }

  override fun getReference(location: String): GoogleStorageReference {
    return delegate.getReference(location)
  }

  override fun getReferenceFromUrl(fullUrl: String): GoogleStorageReference {
    return delegate.getReferenceFromUrl(fullUrl)
  }

  override fun rxGetReference(): StorageReference {
    return StorageReference.newInstance(delegate.reference)
  }

  override fun rxGetReference(location: String): StorageReference {
    return StorageReference.newInstance(delegate.getReference(location))
  }

  override fun rxGetReferenceFromUrl(fullUrl: String): StorageReference {
    return StorageReference.newInstance(delegate.getReferenceFromUrl(fullUrl))
  }

  override fun setMaxDownloadRetryTimeMillis(maxTransferRetryMillis: Long) {
    delegate.maxDownloadRetryTimeMillis = maxTransferRetryMillis
  }

  override fun setMaxOperationRetryTimeMillis(maxTransferRetryMillis: Long) {
    delegate.maxOperationRetryTimeMillis = maxTransferRetryMillis
  }

  override fun setMaxUploadRetryTimeMillis(maxTransferRetryMillis: Long) {
    delegate.maxUploadRetryTimeMillis = maxTransferRetryMillis
  }
}