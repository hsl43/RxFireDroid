package com.labs2160.rxfiredroid.database

import com.google.firebase.FirebaseApp
import com.google.firebase.database.Logger
import com.google.firebase.database.DatabaseReference as GoogleDatabaseReference
import com.google.firebase.database.FirebaseDatabase as GoogleFirebaseDatabase

@Suppress("unused")
internal class FirebaseDatabaseImpl(private val delegate: GoogleFirebaseDatabase) : FirebaseDatabase {
  override fun getApp(): FirebaseApp {
    return delegate.app
  }

  override fun getReference(): GoogleDatabaseReference {
    return delegate.reference
  }

  override fun getReference(path: String): GoogleDatabaseReference {
    return delegate.getReference(path)
  }

  override fun getReferenceFromUrl(url: String): GoogleDatabaseReference {
    return delegate.getReferenceFromUrl(url)
  }

  override fun goOffline() {
    delegate.goOffline()
  }

  override fun goOnline() {
    delegate.goOnline()
  }

  override fun purgeOutstandingWrites() {
    delegate.purgeOutstandingWrites()
  }

  override fun rxGetReference(): DatabaseReference {
    return DatabaseReference.newInstance(delegate.reference)
  }

  override fun rxGetReference(path: String): DatabaseReference {
    return DatabaseReference.newInstance(delegate.getReference(path))
  }

  override fun rxGetReferenceFromUrl(url: String): DatabaseReference {
    return DatabaseReference.newInstance(delegate.getReferenceFromUrl(url))
  }

  @Synchronized override fun setLogLevel(logLevel: Logger.Level) {
    delegate.setLogLevel(logLevel)
  }

  @Synchronized override fun setPersistenceCacheSizeBytes(cacheSizeInBytes: Long) {
    delegate.setPersistenceCacheSizeBytes(cacheSizeInBytes)
  }

  @Synchronized override fun setPersistenceEnabled(isEnabled: Boolean) {
    delegate.setPersistenceEnabled(isEnabled)
  }
}