package com.labs2160.rxfiredroid.database

import com.google.android.gms.tasks.Task
import com.google.firebase.database.MutableData
import io.reactivex.Completable
import io.reactivex.Single
import com.google.firebase.database.DatabaseReference as GoogleDatabaseReference
import com.google.firebase.database.FirebaseDatabase as GoogleFirebaseDatabase
import com.google.firebase.database.OnDisconnect as GoogleOnDisconnect
import com.google.firebase.database.Transaction as GoogleTransaction

interface DatabaseReference {
  @Suppress("unused")
  companion object {
    fun newInstance(googleDatabaseReference: GoogleDatabaseReference): DatabaseReference {
      return DatabaseReferenceImpl(googleDatabaseReference)
    }

    /**
     * Manually disconnect the Firebase Database client from the server and
     * disable automatic reconnection.
     */
    fun goOffline() = GoogleDatabaseReference.goOffline()

    /**
     * Manually reestablish a connection to the Firebase Database server and
     * enable automatic reconnection.
     */
    fun goOnline() = GoogleDatabaseReference.goOnline()
  }

  /**
   * Get a reference to location relative to this one
   */
  fun child(pathString: String): GoogleDatabaseReference

  /**
   * Gets the Database instance associated with this reference.
   */
  fun getDatabase(): GoogleFirebaseDatabase

  fun getKey(): String?

  fun getParent(): GoogleDatabaseReference?

  fun getRoot(): GoogleDatabaseReference

  /**
   * Provides access to disconnect operations at this location
   */
  fun onDisconnect(): GoogleOnDisconnect

  /**
   * Create a reference to an auto-generated child location.
   */
  fun push(): GoogleDatabaseReference

  /**
   * Set the value at this location to 'null'
   */
  fun removeValue(): Task<Void>

  /**
   * Set the value at this location to 'null'
   */
  fun removeValue(listener: GoogleDatabaseReference.CompletionListener)

  /**
   * Run a transaction on the data at this location.
   */
  fun runTransaction(handler: GoogleTransaction.Handler, fireLocalEvents: Boolean)

  /**
   * Run a transaction on the data at this location.
   */
  fun runTransaction(handler: GoogleTransaction.Handler)

  /**
   * Get a reference to location relative to this one
   */
  fun rxChild(pathString: String): DatabaseReference

  /**
   * Gets the Database instance associated with this reference.
   */
  fun rxGetDatabase(): FirebaseDatabase

  fun rxGetParent(): DatabaseReference?

  fun rxGetRoot(): DatabaseReference

  /**
   * Provides access to disconnect operations at this location
   */
  fun rxOnDisconnect(): OnDisconnect

  /**
   * Create a reference to an auto-generated child location.
   */
  fun rxPush(): DatabaseReference

  /**
   * Set the value at this location to 'null'
   */
  fun rxRemoveValue(): Completable

  /**
   * Run a transaction on the data at this location.
   */
  fun rxRunTransaction(fireLocalEvents: Boolean, handler: (MutableData) -> Unit): Single<DataSnapshot>

  /**
   * Run a transaction on the data at this location.
   */
  fun rxRunTransaction(handler: (MutableData) -> Unit): Single<DataSnapshot> {
    return this.rxRunTransaction(true, handler)
  }

  /**
   * Set a priority for the data at this Database location.
   */
  fun rxSetPriority(priority: Any): Completable

  /**
   * Set the data and priority to the given values.
   */
  fun rxSetValue(value: Any, priority: Any): Completable

  /**
   * Set the data at this location to the given value.
   */
  fun rxSetValue(value: Any): Completable

  /**
   * Update the specific child keys to the specified values.
   */
  fun rxUpdateChildren(update: Map<String,Any>): Completable

  /**
   * Set a priority for the data at this Database location.
   */
  fun setPriority(priority: Any, listener: GoogleDatabaseReference.CompletionListener)

  /**
   * Set a priority for the data at this Database location.
   */
  fun setPriority(priority: Any): Task<Void>

  /**
   * Set the data and priority to the given values.
   */
  fun setValue(value: Any, priority: Any, listener: GoogleDatabaseReference.CompletionListener)

  /**
   * Set the data at this location to the given value.
   */
  fun setValue(value: Any, listener: GoogleDatabaseReference.CompletionListener)

  /**
   * Set the data and priority to the given values.
   */
  fun setValue(value: Any, priority: Any): Task<Void>

  /**
   * Set the data at this location to the given value.
   */
  fun setValue(value: Any): Task<Void>

  /**
   * Update the specific child keys to the specified values.
   */
  fun updateChildren(update: Map<String,Any>): Task<Void>

  /**
   * Update the specific child keys to the specified values.
   */
  fun updateChildren(update: Map<String,Any>, listener: GoogleDatabaseReference.CompletionListener)
}