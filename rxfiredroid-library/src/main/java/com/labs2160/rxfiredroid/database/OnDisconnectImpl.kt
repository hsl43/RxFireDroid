package com.labs2160.rxfiredroid.database

import com.google.android.gms.tasks.Task
import com.labs2160.rxfiredroid.addRxOnCompleteListener
import io.reactivex.Completable
import com.google.firebase.database.DatabaseReference as GoogleDatabaseReference
import com.google.firebase.database.OnDisconnect as GoogleOnDisconnect

@Suppress("unused")
internal class OnDisconnectImpl(private val delegate: GoogleOnDisconnect) : OnDisconnect {
  override fun cancel(): Task<Void> {
    return delegate.cancel()
  }

  override fun cancel(listener: GoogleDatabaseReference.CompletionListener) {
    return delegate.cancel(listener)
  }

  override fun removeValue(): Task<Void> {
    return delegate.removeValue()
  }

  override fun removeValue(listener: GoogleDatabaseReference.CompletionListener) {
    return delegate.removeValue(listener)
  }

  override fun rxCancel(): Completable {
    return Completable.create { emitter ->
      delegate.cancel()
          .addRxOnCompleteListener(emitter, "Unexpected error in rxCancel()")
    }
  }

  override fun rxRemoveValue(): Completable {
    return Completable.create { emitter ->
      delegate.removeValue()
          .addRxOnCompleteListener(emitter, "Unexpected error in rxRemoveValue()")
    }
  }

  override fun rxSetValue(value: Any, priority: String): Completable {
    return Completable.create { emitter ->
      delegate.setValue(value, priority)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxSetValue()")
    }
  }

  override fun rxSetValue(value: Any, priority: Double): Completable {
    return Completable.create { emitter ->
      delegate.setValue(value, priority)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxSetValue()")
    }
  }

  override fun rxSetValue(value: Any): Completable {
    return Completable.create { emitter ->
      delegate.setValue(value)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxSetValue()")
    }
  }

  override fun rxUpdateChildren(update: Map<String,Any>): Completable {
    return Completable.create { emitter ->
      delegate.updateChildren(update)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxUpdateChildren()")
    }
  }

  override fun setValue(value: Any, priority: Double, listener: GoogleDatabaseReference.CompletionListener) {
    return delegate.setValue(value, priority, listener)
  }

  override fun setValue(value: Any, priority: String): Task<Void> {
    return delegate.setValue(value, priority)
  }

  override fun setValue(value: Any, priority: Double): Task<Void> {
    return delegate.setValue(value, priority)
  }

  override fun setValue(value: Any, priority: String, listener: GoogleDatabaseReference.CompletionListener) {
    return delegate.setValue(value, priority, listener)
  }

  override fun setValue(value: Any, listener: GoogleDatabaseReference.CompletionListener) {
    return delegate.setValue(value, listener)
  }

  override fun setValue(value: Any): Task<Void> {
    return delegate.setValue(value)
  }

  override fun updateChildren(update: Map<String,Any>): Task<Void> {
    return delegate.updateChildren(update)
  }

  override fun updateChildren(update: Map<String,Any>, listener: GoogleDatabaseReference.CompletionListener) {
    return delegate.updateChildren(update, listener)
  }
}