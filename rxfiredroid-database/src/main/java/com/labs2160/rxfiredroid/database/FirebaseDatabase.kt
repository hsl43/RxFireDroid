package com.labs2160.rxfiredroid.database

import com.google.firebase.FirebaseApp
import com.google.firebase.database.Logger
import com.google.firebase.database.DatabaseReference as GoogleDatabaseReference
import com.google.firebase.database.FirebaseDatabase as GoogleFirebaseDatabase

interface FirebaseDatabase {
  companion object {
    private val lock = Any()

    private var instance: FirebaseDatabaseImpl? = null

    fun getInstance(): FirebaseDatabase {
      return getInstance(app = null, url = null)
    }

    fun getInstance(app: FirebaseApp): FirebaseDatabase {
      return getInstance(app = app, url = null)
    }

    fun getInstance(url: String): FirebaseDatabase {
      return getInstance(app = null, url = url)
    }

    fun getInstance(app: FirebaseApp? = null, url: String? = null): FirebaseDatabase {
      synchronized(lock) {
        if(instance == null) {
          val delegate = when {
            app != null && url != null -> GoogleFirebaseDatabase.getInstance(app, url)
            app != null && url == null -> GoogleFirebaseDatabase.getInstance(app)
            app == null && url != null -> GoogleFirebaseDatabase.getInstance(url)
                                  else -> GoogleFirebaseDatabase.getInstance()
          }

          instance = FirebaseDatabaseImpl(delegate)
        }
      }

      return instance!!
    }

    @Suppress("unused")
    fun getSdkVersion(): String {
      return GoogleFirebaseDatabase.getSdkVersion()
    }
  }

  /**
   * Returns the FirebaseApp instance to which this FirebaseDatabase belongs.
   */
  fun getApp(): FirebaseApp

  /**
   * Gets a DatabaseReference for the database root node.
   */
  fun getReference(): GoogleDatabaseReference

  /**
   * Gets a DatabaseReference for the provided path.
   */
  fun getReference(path: String): GoogleDatabaseReference

  /**
   * Gets a DatabaseReference for the provided URL.
   */
  fun getReferenceFromUrl(url: String): GoogleDatabaseReference

  /**
   * Shuts down our connection to the Firebase Database backend until
   * goOnline() is called.
   */
  fun goOffline()

  /**
   * Resumes our connection to the Firebase Database backend after a previous
   * goOffline() call.
   */
  fun goOnline()

  /**
   * The Firebase Database client automatically queues writes and sends them to
   * the server at the earliest opportunity, depending on network connectivity.
   */
  fun purgeOutstandingWrites()

  /**
   * Gets a DatabaseReference for the database root node.
   */
  fun rxGetReference(): DatabaseReference

  /**
   * Gets a DatabaseReference for the provided path.
   */
  fun rxGetReference(path: String): DatabaseReference

  /**
   * Gets a DatabaseReference for the provided URL.
   */
  fun rxGetReferenceFromUrl(url: String): DatabaseReference

  /**
   * By default, this is set to INFO.
   */
  fun setLogLevel(logLevel: Logger.Level)

  /**
   * By default Firebase Database will use up to 10MB of disk space to cache
   * data.
   */
  fun setPersistenceCacheSizeBytes(cacheSizeInBytes: Long)

  /**
   * The Firebase Database client will cache synchronized data and keep track
   * of all writes you've initiated while your application is running.
   */
  fun setPersistenceEnabled(isEnabled: Boolean)
}