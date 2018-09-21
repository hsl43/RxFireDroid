package com.labs2160.rxfiredroid.database

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import com.google.firebase.database.DataSnapshot as GoogleDataSnapshot
import com.google.firebase.database.DatabaseReference as GoogleDatabaseReference
import com.google.firebase.database.Query as GoogleQuery

@Suppress("unused")
internal class QueryImpl(private val delegate: GoogleQuery) : Query {
  override fun addChildEventListener(listener: ChildEventListener): ChildEventListener {
    return delegate.addChildEventListener(listener)
  }

  override fun addListenerForSingleValueEvent(listener: ValueEventListener) {
    return delegate.addListenerForSingleValueEvent(listener)
  }

  override fun addValueEventListener(listener: ValueEventListener): ValueEventListener {
    return delegate.addValueEventListener(listener)
  }

  override fun endAt(value: String, key: String): GoogleQuery {
    return delegate.endAt(value, key)
  }

  override fun endAt(value: String): GoogleQuery {
    return delegate.endAt(value)
  }

  override fun endAt(value: Double, key: String): GoogleQuery {
    return delegate.endAt(value, key)
  }

  override fun endAt(value: Double): GoogleQuery {
    return delegate.endAt(value)
  }

  override fun endAt(value: Boolean): GoogleQuery {
    return delegate.endAt(value)
  }

  override fun endAt(value: Boolean, key: String): GoogleQuery {
    return delegate.endAt(value, key)
  }

  override fun equalTo(value: String, key: String): GoogleQuery {
    return delegate.endAt(value, key)
  }

  override fun equalTo(value: Double): GoogleQuery {
    return delegate.endAt(value)
  }

  override fun equalTo(value: Double, key: String): GoogleQuery {
    return delegate.endAt(value, key)
  }

  override fun equalTo(value: String): GoogleQuery {
    return delegate.endAt(value)
  }

  override fun equalTo(value: Boolean, key: String): GoogleQuery {
    return delegate.endAt(value, key)
  }

  override fun equalTo(value: Boolean): GoogleQuery {
    return delegate.endAt(value)
  }

  override fun getRef(): GoogleDatabaseReference {
    return delegate.ref
  }

  override fun keepSynced(keepSynced: Boolean) {
    return delegate.keepSynced(keepSynced)
  }

  override fun limitToFirst(limit: Int): GoogleQuery {
    return delegate.limitToFirst(limit)
  }

  override fun limitToLast(limit: Int): GoogleQuery {
    return delegate.limitToLast(limit)
  }

  override fun orderByChild(path: String): GoogleQuery {
    return delegate.orderByChild(path)
  }

  override fun orderByKey(): GoogleQuery {
    return delegate.orderByKey()
  }

  override fun orderByPriority(): GoogleQuery {
    return delegate.orderByPriority()
  }

  override fun orderByValue(): GoogleQuery {
    return delegate.orderByValue()
  }

  override fun removeEventListener(listener: ChildEventListener) {
    delegate.removeEventListener(listener)
  }

  override fun removeEventListener(listener: ValueEventListener) {
    delegate.removeEventListener(listener)
  }

  override fun rxBindChildEvents(backpressureStrategy: BackpressureStrategy): Flowable<ChildDataSnapshotState> {
    val source = FlowableOnSubscribe<ChildDataSnapshotState> { emitter ->
      val listener = object : ChildEventListener {
        override fun onCancelled(error: DatabaseError) {
          emitter.onError(error.toException())
        }

        override fun onChildMoved(snapshot: GoogleDataSnapshot, previousChildName: String?) {
          emitter.onNext(ChildDataSnapshotState(
              ChildAction.Moved,
              DataSnapshot.newInstance(snapshot),
              previousChildName
          ))
        }

        override fun onChildChanged(snapshot: GoogleDataSnapshot, previousChildName: String?) {
          emitter.onNext(ChildDataSnapshotState(
              ChildAction.Changed,
              DataSnapshot.newInstance(snapshot),
              previousChildName
          ))
        }

        override fun onChildAdded(snapshot: GoogleDataSnapshot, previousChildName: String?) {
          emitter.onNext(ChildDataSnapshotState(
              ChildAction.Added,
              DataSnapshot.newInstance(snapshot),
              previousChildName
          ))
        }

        override fun onChildRemoved(snapshot: GoogleDataSnapshot) {
          emitter.onNext(ChildDataSnapshotState(
              ChildAction.Removed,
              DataSnapshot.newInstance(snapshot)
          ))
        }
      }

      delegate.addChildEventListener(listener)

      var unregistered = false

      emitter.setDisposable(object : Disposable {
        override fun isDisposed(): Boolean = unregistered

        override fun dispose() {
          unregistered = true

          delegate.removeEventListener(listener)
        }
      })
    }

    return Flowable.create(source, backpressureStrategy)
  }

  override fun rxBindSingleValueEvent(): Single<DataSnapshot> {
    return Single.create { emitter ->
      val listener = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {
          emitter.onError(error.toException())
        }

        override fun onDataChange(snapshot: GoogleDataSnapshot) {
          emitter.onSuccess(DataSnapshot.newInstance(snapshot))
        }
      }

      delegate.addListenerForSingleValueEvent(listener)

      var unregistered = false

      emitter.setDisposable(object : Disposable {
        override fun isDisposed(): Boolean = unregistered

        override fun dispose() {
          unregistered = true

          delegate.removeEventListener(listener)
        }
      })
    }
  }

  override fun rxBindValueEvent(backpressureStrategy: BackpressureStrategy): Flowable<DataSnapshot> {
    val source = FlowableOnSubscribe<DataSnapshot> { emitter ->
      val listener = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {
          emitter.onError(error.toException())
        }

        override fun onDataChange(snapshot: GoogleDataSnapshot) {
          emitter.onNext(DataSnapshot.newInstance(snapshot))
        }
      }

      delegate.addValueEventListener(listener)

      var unregistered = false

      emitter.setDisposable(object : Disposable {
        override fun isDisposed(): Boolean = unregistered

        override fun dispose() {
          unregistered = true

          delegate.removeEventListener(listener)
        }
      })
    }

    return Flowable.create(source, backpressureStrategy)
  }

  override fun rxEndAt(value: String, key: String): Query {
    return QueryImpl(delegate.endAt(value, key))
  }

  override fun rxEndAt(value: String): Query {
    return QueryImpl(delegate.endAt(value))
  }

  override fun rxEndAt(value: Double, key: String): Query {
    return QueryImpl(delegate.endAt(value, key))
  }

  override fun rxEndAt(value: Double): Query {
    return QueryImpl(delegate.endAt(value))
  }

  override fun rxEndAt(value: Boolean): Query {
    return QueryImpl(delegate.endAt(value))
  }

  override fun rxEndAt(value: Boolean, key: String): Query {
    return QueryImpl(delegate.endAt(value, key))
  }

  override fun rxEqualTo(value: String, key: String): Query {
    return QueryImpl(delegate.equalTo(value, key))
  }

  override fun rxEqualTo(value: Double): Query {
    return QueryImpl(delegate.equalTo(value))
  }

  override fun rxEqualTo(value: Double, key: String): Query {
    return QueryImpl(delegate.equalTo(value, key))
  }

  override fun rxEqualTo(value: String): Query {
    return QueryImpl(delegate.equalTo(value))
  }

  override fun rxEqualTo(value: Boolean, key: String): Query {
    return QueryImpl(delegate.equalTo(value, key))
  }

  override fun rxEqualTo(value: Boolean): Query {
    return QueryImpl(delegate.equalTo(value))
  }

  override fun rxGetRef(): DatabaseReference {
    return DatabaseReference.newInstance(delegate.ref)
  }

  override fun rxLimitToFirst(limit: Int): Query {
    return QueryImpl(delegate.limitToFirst(limit))
  }

  override fun rxLimitToLast(limit: Int): Query {
    return QueryImpl(delegate.limitToLast(limit))
  }

  override fun rxOrderByChild(path: String): Query {
    return QueryImpl(delegate.orderByChild(path))
  }

  override fun rxOrderByKey(): Query {
    return QueryImpl(delegate.orderByKey())
  }

  override fun rxOrderByPriority(): Query {
    return QueryImpl(delegate.orderByPriority())
  }

  override fun rxOrderByValue(): Query {
    return QueryImpl(delegate.orderByValue())
  }

  override fun rxStartAt(value: Double): Query {
    return QueryImpl(delegate.startAt(value))
  }

  override fun rxStartAt(value: String, key: String): Query {
    return QueryImpl(delegate.startAt(value, key))
  }

  override fun rxStartAt(value: String): Query {
    return QueryImpl(delegate.startAt(value))
  }

  override fun rxStartAt(value: Double, key: String): Query {
    return QueryImpl(delegate.startAt(value, key))
  }

  override fun rxStartAt(value: Boolean): Query {
    return QueryImpl(delegate.startAt(value))
  }

  override fun rxStartAt(value: Boolean, key: String): Query {
    return QueryImpl(delegate.startAt(value, key))
  }

  override fun startAt(value: Double): GoogleQuery {
    return delegate.startAt(value)
  }

  override fun startAt(value: String, key: String): GoogleQuery {
    return delegate.startAt(value, key)
  }

  override fun startAt(value: String): GoogleQuery {
    return delegate.startAt(value)
  }

  override fun startAt(value: Double, key: String): GoogleQuery {
    return delegate.startAt(value, key)
  }

  override fun startAt(value: Boolean): GoogleQuery {
    return delegate.startAt(value)
  }

  override fun startAt(value: Boolean, key: String): GoogleQuery {
    return delegate.startAt(value, key)
  }
}