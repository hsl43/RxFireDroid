package com.labs2160.rxfiredroid.database

import com.google.android.gms.tasks.Task
import io.reactivex.Completable
import com.google.firebase.database.DatabaseReference as GoogleDatabaseReference
import com.google.firebase.database.OnDisconnect as GoogleOnDisconnect

interface OnDisconnect {
  companion object {
    fun newInstance(googleOnDisconnect: GoogleOnDisconnect): OnDisconnect {
      return OnDisconnectImpl(googleOnDisconnect)
    }
  }

  /**
   * Cancel any disconnect operations that are queued up at this location
   */
  fun cancel(): Task<Void>

  /**
   * Cancel any disconnect operations that are queued up at this location
   */
  fun cancel(listener: GoogleDatabaseReference.CompletionListener)

  /**
   * Remove the value at this location when the client disconnects
   */
  fun removeValue(): Task<Void>

  /**
   * Remove the value at this location when the client disconnects
   */
  fun removeValue(listener: GoogleDatabaseReference.CompletionListener)

  /**
   * Cancel any disconnect operations that are queued up at this location
   */
  fun rxCancel(): Completable

  /**
   * Remove the value at this location when the client disconnects
   */
  fun rxRemoveValue(): Completable

  /**
   * Ensure the data at this location is set to the specified value and
   * priority when the client is disconnected (due to closing the browser,
   * navigating to a new page, or network issues).
   */
  fun rxSetValue(value: Any, priority: String): Completable

  /**
   * Ensure the data at this location is set to the specified value and
   * priority when the client is disconnected (due to closing the browser,
   * navigating to a new page, or network issues).
   */
  fun rxSetValue(value: Any, priority: Double): Completable

  /**
   * Ensure the data at this location is set to the specified value when the
   * client is disconnected (due to closing the browser, navigating to a new
   * page, or network issues).
   */
  fun rxSetValue(value: Any): Completable

  /**
   * Ensure the data has the specified child values updated when the client is
   * disconnected
   */
  fun rxUpdateChildren(update: Map<String,Any>): Completable

  /**
   * Ensure the data at this location is set to the specified value and
   * priority when the client is disconnected (due to closing the browser,
   * navigating to a new page, or network issues).
   */
  fun setValue(value: Any, priority: Double, listener: GoogleDatabaseReference.CompletionListener)

  /**
   * Ensure the data at this location is set to the specified value and
   * priority when the client is disconnected (due to closing the browser,
   * navigating to a new page, or network issues).
   */
  fun setValue(value: Any, priority: String): Task<Void>

  /**
   * Ensure the data at this location is set to the specified value and
   * priority when the client is disconnected (due to closing the browser,
   * navigating to a new page, or network issues).
   */
  fun setValue(value: Any, priority: Double): Task<Void>

  /**
   * Ensure the data at this location is set to the specified value and
   * priority when the client is disconnected (due to closing the browser,
   * navigating to a new page, or network issues).
   */
  fun setValue(value: Any, priority: String, listener: GoogleDatabaseReference.CompletionListener)

  /**
   * Ensure the data at this location is set to the specified value when the
   * client is disconnected (due to closing the browser, navigating to a new
   * page, or network issues).
   */
  fun setValue(value: Any, listener: GoogleDatabaseReference.CompletionListener)

  /**
   * Ensure the data at this location is set to the specified value when the
   * client is disconnected (due to closing the browser, navigating to a new
   * page, or network issues).
   */
  fun setValue(value: Any): Task<Void>

  /**
   * Ensure the data has the specified child values updated when the client is
   * disconnected
   */
  fun updateChildren(update: Map<String,Any>): Task<Void>

  /**
   * Ensure the data has the specified child values updated when the client is
   * disconnected
   */
  fun updateChildren(update: Map<String,Any>, listener: GoogleDatabaseReference.CompletionListener)
}