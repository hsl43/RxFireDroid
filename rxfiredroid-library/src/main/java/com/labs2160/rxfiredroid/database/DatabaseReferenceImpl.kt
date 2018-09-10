package com.labs2160.rxfiredroid.database

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.MutableData
import com.labs2160.rxfiredroid.addRxOnCompleteListener
import io.reactivex.Completable
import io.reactivex.Single
import com.google.firebase.database.DataSnapshot as GoogleDataSnapshot
import com.google.firebase.database.DatabaseReference as GoogleDatabaseReference
import com.google.firebase.database.FirebaseDatabase as GoogleFirebaseDatabase
import com.google.firebase.database.OnDisconnect as GoogleOnDisconnect
import com.google.firebase.database.Transaction as GoogleTransaction

@Suppress("unused")
internal class DatabaseReferenceImpl(private val delegate: GoogleDatabaseReference) : DatabaseReference {
  override fun child(pathString: String): GoogleDatabaseReference {
    return delegate.child(pathString)
  }

  override fun getDatabase(): GoogleFirebaseDatabase {
    return delegate.database
  }

  override fun getKey(): String? {
    return delegate.key
  }

  override fun getParent(): GoogleDatabaseReference? {
    return delegate.parent
  }

  override fun getRoot(): GoogleDatabaseReference {
    return delegate.root
  }

  override fun onDisconnect(): GoogleOnDisconnect {
    return delegate.onDisconnect()
  }

  override fun push(): GoogleDatabaseReference {
    return delegate.push()
  }

  override fun removeValue(): Task<Void> {
    return delegate.removeValue()
  }

  override fun removeValue(listener: GoogleDatabaseReference.CompletionListener) {
    return delegate.removeValue(listener)
  }

  override fun runTransaction(handler: GoogleTransaction.Handler, fireLocalEvents: Boolean) {
    return delegate.runTransaction(handler, fireLocalEvents)
  }

  override fun runTransaction(handler: GoogleTransaction.Handler) {
    return delegate.runTransaction(handler)
  }

  override fun rxChild(pathString: String): DatabaseReference {
    return DatabaseReferenceImpl(delegate.child(pathString))
  }

  override fun rxGetDatabase(): FirebaseDatabase {
    return FirebaseDatabase.getInstance()
  }

  override fun rxGetParent(): DatabaseReference? {
    val parent = delegate.parent

    return if(parent == null) {
      null
    } else {
      DatabaseReferenceImpl(parent)
    }
  }

  override fun rxGetRoot(): DatabaseReference {
    return DatabaseReferenceImpl(delegate.root)
  }

  override fun rxOnDisconnect(): OnDisconnect {
    return OnDisconnect.newInstance(delegate.onDisconnect())
  }

  override fun rxPush(): DatabaseReference {
    return DatabaseReferenceImpl(delegate.push())
  }

  override fun rxRemoveValue(): Completable {
    return Completable.create { emitter ->
      delegate.removeValue()
          .addRxOnCompleteListener(emitter, "Unexpected error in rxRemoveValue()")
    }
  }

  override fun rxRunTransaction(fireLocalEvents: Boolean, handler: (MutableData) -> Unit): Single<DataSnapshot> {
    return Single.create { emitter ->
      val transactionHandler = object : GoogleTransaction.Handler {
        override fun onComplete(error: DatabaseError?, committed: Boolean, currentData: GoogleDataSnapshot?) {
          if(currentData != null && committed) {
            emitter.onSuccess(DataSnapshot.newInstance(currentData))
          } else {
            emitter.onError(error?.toException() ?: RuntimeException("Unexpected error or abort in rxRunTransaction()"))
          }
        }

        override fun doTransaction(currentData: MutableData): GoogleTransaction.Result {
          handler(currentData)

          return GoogleTransaction.success(currentData)
        }
      }

      delegate.runTransaction(transactionHandler, fireLocalEvents)
    }
  }

  override fun rxSetPriority(priority: Any): Completable {
    return Completable.create { emitter ->
      delegate.setPriority(priority)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxSetPriority()")
    }
  }

  override fun rxSetValue(value: Any, priority: Any): Completable {
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

  override fun setPriority(priority: Any, listener: GoogleDatabaseReference.CompletionListener) {
    return delegate.setPriority(priority, listener)
  }

  override fun setPriority(priority: Any): Task<Void> {
    return delegate.setPriority(priority)
  }

  override fun setValue(value: Any, priority: Any, listener: GoogleDatabaseReference.CompletionListener) {
    return delegate.setValue(value, priority, listener)
  }

  override fun setValue(value: Any, listener: GoogleDatabaseReference.CompletionListener) {
    return delegate.setValue(value, listener)
  }

  override fun setValue(value: Any, priority: Any): Task<Void> {
    return delegate.setValue(value, priority)
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