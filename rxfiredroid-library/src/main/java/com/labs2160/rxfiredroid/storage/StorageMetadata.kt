package com.labs2160.rxfiredroid.storage

import com.google.firebase.storage.StorageMetadata as GoogleStorageMetadata
import com.google.firebase.storage.StorageReference as GoogleStorageReference

interface StorageMetadata {
  companion object {
    fun newInstance(googleStorageMetadata: GoogleStorageMetadata): StorageMetadata {
      return StorageMetadataImpl(googleStorageMetadata)
    }
  }

  fun getBucket(): String?
  fun getCacheControl(): String?
  fun getContentDisposition(): String?
  fun getContentEncoding(): String?
  fun getContentLanguage(): String?
  fun getContentType(): String
  fun getCreationTimeMillis(): Long
  fun getCustomMetadata(key: String): String
  fun getCustomMetadataKeys(): Set<String>
  fun getGeneration(): String?
  fun getMd5Hash(): String?
  fun getMetadataGeneration(): String?
  fun getName(): String?
  fun getPath(): String
  fun getReference(): GoogleStorageReference?
  fun getSizeBytes(): Long
  fun getUpdatedTimeMillis(): Long
  fun rxGetReference(): StorageReference?
}