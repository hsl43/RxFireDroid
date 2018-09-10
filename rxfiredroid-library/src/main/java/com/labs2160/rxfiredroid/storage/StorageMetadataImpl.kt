package com.labs2160.rxfiredroid.storage

import com.google.firebase.storage.StorageMetadata as GoogleStorageMetadata
import com.google.firebase.storage.StorageReference as GoogleStorageReference

internal class StorageMetadataImpl(private val delegate: GoogleStorageMetadata) : StorageMetadata {
  override fun getBucket(): String? {
    return delegate.bucket
  }

  override fun getCacheControl(): String? {
    return delegate.cacheControl
  }

  override fun getContentDisposition(): String? {
    return delegate.contentDisposition
  }

  override fun getContentEncoding(): String? {
    return delegate.contentEncoding
  }

  override fun getContentLanguage(): String? {
    return delegate.contentLanguage
  }

  override fun getContentType(): String {
    return delegate.contentType
  }

  override fun getCreationTimeMillis(): Long {
    return delegate.creationTimeMillis
  }

  override fun getCustomMetadata(key: String): String {
    return delegate.getCustomMetadata(key)
  }

  override fun getCustomMetadataKeys(): Set<String> {
    return delegate.customMetadataKeys
  }

  override fun getGeneration(): String? {
    return delegate.generation
  }

  override fun getMd5Hash(): String? {
    return delegate.md5Hash
  }

  override fun getMetadataGeneration(): String? {
    return delegate.metadataGeneration
  }

  override fun getName(): String? {
    return delegate.name
  }

  override fun getPath(): String {
    return delegate.path
  }

  override fun getReference(): GoogleStorageReference? {
    return delegate.reference
  }

  override fun getSizeBytes(): Long {
    return delegate.sizeBytes
  }

  override fun getUpdatedTimeMillis(): Long {
    return delegate.updatedTimeMillis
  }

  override fun rxGetReference(): StorageReference? {
    val ref = delegate.reference

    return if(ref == null) {
      null
    } else {
      StorageReference.newInstance(ref)
    }
  }
}