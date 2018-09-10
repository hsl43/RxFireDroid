package com.labs2160.rxfiredroid.firestore

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.Single
import java.util.concurrent.Executor
import com.google.firebase.firestore.CollectionReference as GoogleCollectionReference
import com.google.firebase.firestore.DocumentReference as GoogleDocumentReference
import com.google.firebase.firestore.FirebaseFirestore as GoogleFirebaseFirestore
import com.google.firebase.firestore.Query as GoogleQuery
import com.google.firebase.firestore.QuerySnapshot as GoogleQuerySnapshot

@Suppress("unused")
internal class QueryableCollectionReference : CollectionReference  {
  private val collectionDelegate: GoogleCollectionReference
  private var queryDelegate: GoogleQuery? = null

  constructor(collectionDelegate: GoogleCollectionReference) {
    this.collectionDelegate = collectionDelegate
  }

  constructor(googleCollectionReference: GoogleCollectionReference, googleQuery: GoogleQuery) {
    this.collectionDelegate = googleCollectionReference
    this.queryDelegate = googleQuery
  }

  companion object {
    fun newInstance(googleCollectionReference: GoogleCollectionReference): CollectionReference {
      return QueryableCollectionReference(googleCollectionReference)
    }

    fun newInstance(googleCollectionReference: GoogleCollectionReference, googleQuery: GoogleQuery): CollectionReference {
      return QueryableCollectionReference(googleCollectionReference, googleQuery)
    }
  }

  override fun add(data: Map<String,Any>): Task<GoogleDocumentReference> {
    return collectionDelegate.add(data)
  }

  override fun add(pojo: Any): Task<GoogleDocumentReference> {
    return collectionDelegate.add(pojo)
  }

  override fun document(): GoogleDocumentReference {
    return collectionDelegate.document()
  }

  override fun document(path: String): GoogleDocumentReference {
    return collectionDelegate.document(path)
  }

  override fun getId(): String {
    return collectionDelegate.id
  }

  override fun getParent(): GoogleDocumentReference? {
    return collectionDelegate.parent
  }

  override fun getPath(): String {
    return collectionDelegate.path
  }

  override fun addSnapshotListener(activity: Activity, listener: EventListener<GoogleQuerySnapshot>): ListenerRegistration {
    return collectionDelegate.addSnapshotListener(activity, listener)
  }

  override fun addSnapshotListener(metadataChanges: MetadataChanges, listener: EventListener<GoogleQuerySnapshot>): ListenerRegistration {
    return collectionDelegate.addSnapshotListener(metadataChanges, listener)
  }

  override fun addSnapshotListener(executor: Executor, listener: EventListener<GoogleQuerySnapshot>): ListenerRegistration {
    return collectionDelegate.addSnapshotListener(executor, listener)
  }

  override fun addSnapshotListener(
      executor: Executor,
      metadataChanges: MetadataChanges,
      listener: EventListener<GoogleQuerySnapshot>

  ): ListenerRegistration {

    return collectionDelegate.addSnapshotListener(executor, metadataChanges, listener)
  }

  override fun addSnapshotListener(
      activity: Activity,
      metadataChanges: MetadataChanges,
      listener: EventListener<GoogleQuerySnapshot>

  ): ListenerRegistration {

    return collectionDelegate.addSnapshotListener(activity, metadataChanges, listener)
  }

  override fun addSnapshotListener(listener: EventListener<GoogleQuerySnapshot>): ListenerRegistration {
    return collectionDelegate.addSnapshotListener(listener)
  }

  override fun endAt(snapshot: DocumentSnapshot): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).endAt(snapshot)

    return queryDelegate!!
  }

  override fun endAt(vararg fieldValues: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).endAt(fieldValues)

    return queryDelegate!!
  }

  override fun endBefore(snapshot: DocumentSnapshot): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).endBefore(snapshot)

    return queryDelegate!!
  }

  override fun endBefore(vararg fieldValues: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).endBefore(fieldValues)

    return queryDelegate!!
  }

  override fun get(): Task<GoogleQuerySnapshot> {
    val delegate = queryDelegate ?: throw IllegalStateException("get() was invoked without a backing Query")

    return delegate.get()
  }

  override fun get(source: Source): Task<GoogleQuerySnapshot> {
    val delegate = queryDelegate ?: throw IllegalStateException("get() was invoked without a backing Query")

    return delegate.get(source)
  }

  override fun getFirestore(): GoogleFirebaseFirestore {
    return collectionDelegate.firestore
  }

  override fun limit(limit: Long): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).limit(limit)

    return queryDelegate!!
  }

  override fun orderBy(field: String, direction: GoogleQuery.Direction): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).orderBy(field, direction)

    return queryDelegate!!
  }

  override fun orderBy(fieldPath: FieldPath): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).orderBy(fieldPath)

    return queryDelegate!!
  }

  override fun orderBy(fieldPath: FieldPath, direction: GoogleQuery.Direction): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).orderBy(fieldPath, direction)

    return queryDelegate!!
  }

  override fun orderBy(field: String): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).orderBy(field)

    return queryDelegate!!
  }

  override fun rxAdd(data: Map<String,Any>): Single<DocumentReference> {
    return Single.create { emitter ->
      collectionDelegate.add(data)
          .addOnCompleteListener { task ->
            if(task.isSuccessful) {
              emitter.onSuccess(DocumentReference.newInstance(task.result))
            } else {
              emitter.onError(task.exception ?: RuntimeException("Unexpected error in rxAdd()"))
            }
          }
    }
  }

  override fun rxAdd(pojo: Any): Single<DocumentReference> {
    return Single.create { emitter ->
      collectionDelegate.add(pojo)
          .addOnCompleteListener { task ->
            if(task.isSuccessful) {
              emitter.onSuccess(DocumentReference.newInstance(task.result))
            } else {
              emitter.onError(task.exception ?: RuntimeException("Unexpected error in rxAdd()"))
            }
          }
    }
  }

  override fun rxBindSnapshot(activity: Activity, backpressureStrategy: BackpressureStrategy): Flowable<QuerySnapshot> {
    val delegate = queryDelegate ?: throw IllegalStateException("Attempt to observe uninitialized Query instance")

    val source = FlowableOnSubscribe<QuerySnapshot> { emitter ->
      val listener = EventListener<GoogleQuerySnapshot> { value, error ->
        if(value != null) {
          emitter.onNext(QuerySnapshot.newInstance(value))
        } else if(error != null) {
          emitter.onError(error)
        }
      }

      delegate.addSnapshotListener(activity, listener)
    }

    return Flowable.create(source, backpressureStrategy)
  }

  override fun rxBindSnapshot(metadataChanges: MetadataChanges, backpressureStrategy: BackpressureStrategy): Flowable<QuerySnapshot> {
    val delegate = queryDelegate ?: throw IllegalStateException("Attempt to observe uninitialized Query instance")

    val source = FlowableOnSubscribe<QuerySnapshot> { emitter ->
      val listener = EventListener<GoogleQuerySnapshot> { value, error ->
        if(value != null) {
          emitter.onNext(QuerySnapshot.newInstance(value))
        } else if(error != null) {
          emitter.onError(error)
        }
      }

      delegate.addSnapshotListener(metadataChanges, listener)
    }

    return Flowable.create(source, backpressureStrategy)
  }

  override fun rxBindSnapshot(executor: Executor, backpressureStrategy: BackpressureStrategy): Flowable<QuerySnapshot> {
    val delegate = queryDelegate ?: throw IllegalStateException("Attempt to observe uninitialized Query instance")

    val source = FlowableOnSubscribe<QuerySnapshot> { emitter ->
      val listener = EventListener<GoogleQuerySnapshot> { value, error ->
        if(value != null) {
          emitter.onNext(QuerySnapshot.newInstance(value))
        } else if(error != null) {
          emitter.onError(error)
        }
      }

      delegate.addSnapshotListener(executor, listener)
    }

    return Flowable.create(source, backpressureStrategy)
  }

  override fun rxBindSnapshot(
      executor: Executor,
      metadataChanges: MetadataChanges,
      backpressureStrategy: BackpressureStrategy

  ): Flowable<QuerySnapshot> {

    val delegate = queryDelegate ?: throw IllegalStateException("Attempt to observe uninitialized Query instance")

    val source = FlowableOnSubscribe<QuerySnapshot> { emitter ->
      val listener = EventListener<GoogleQuerySnapshot> { value, error ->
        if(value != null) {
          emitter.onNext(QuerySnapshot.newInstance(value))
        } else if(error != null) {
          emitter.onError(error)
        }
      }

      delegate.addSnapshotListener(executor, metadataChanges, listener)
    }

    return Flowable.create(source, backpressureStrategy)
  }

  override fun rxBindSnapshot(
      activity: Activity,
      metadataChanges: MetadataChanges,
      backpressureStrategy: BackpressureStrategy

  ): Flowable<QuerySnapshot> {

    val delegate = queryDelegate ?: throw IllegalStateException("Attempt to observe uninitialized Query instance")

    val source = FlowableOnSubscribe<QuerySnapshot> { emitter ->
      val listener = EventListener<GoogleQuerySnapshot> { value, error ->
        if(value != null) {
          emitter.onNext(QuerySnapshot.newInstance(value))
        } else if(error != null) {
          emitter.onError(error)
        }
      }

      delegate.addSnapshotListener(activity, metadataChanges, listener)
    }

    return Flowable.create(source, backpressureStrategy)
  }

  override fun rxBindSnapshot(backpressureStrategy: BackpressureStrategy): Flowable<QuerySnapshot> {
    val delegate = queryDelegate ?: throw IllegalStateException("Attempt to observe uninitialized Query instance")

    val source = FlowableOnSubscribe<QuerySnapshot> { emitter ->
      val listener = EventListener<GoogleQuerySnapshot> { value, error ->
        if(value != null) {
          emitter.onNext(QuerySnapshot.newInstance(value))
        } else if(error != null) {
          emitter.onError(error)
        }
      }

      delegate.addSnapshotListener(listener)
    }

    return Flowable.create(source, backpressureStrategy)
  }

  override fun rxDocument(): DocumentReference {
    return DocumentReference.newInstance(collectionDelegate.document())
  }

  override fun rxDocument(path: String): DocumentReference {
    return DocumentReference.newInstance(collectionDelegate.document(path))
  }

  override fun rxEndAt(snapshot: DocumentSnapshot): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).endAt(snapshot)

    return this
  }

  override fun rxEndAt(vararg fieldValues: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).endAt(fieldValues)

    return this
  }

  override fun rxEndBefore(snapshot: DocumentSnapshot): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).endBefore(snapshot)

    return this
  }

  override fun rxEndBefore(vararg fieldValues: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).endBefore(fieldValues)

    return this
  }

  override fun rxGet(): Single<QuerySnapshot> {
    return Single.create { emitter ->
      queryDelegate!!.get()
          .addOnCompleteListener { task ->
            if(task.isSuccessful) {
              emitter.onSuccess(QuerySnapshot.newInstance(task.result))
            } else {
              emitter.onError(task.exception ?: RuntimeException("Unexpected error in rxGet()"))
            }
          }
    }
  }

  override fun rxGet(source: Source): Single<QuerySnapshot> {
    return Single.create { emitter ->
      collectionDelegate.get(source)
          .addOnCompleteListener { task ->
            if(task.isSuccessful) {
              emitter.onSuccess(QuerySnapshot.newInstance(task.result))
            } else {
              emitter.onError(task.exception ?: RuntimeException("Unexpected error in rxGet()"))
            }
          }
    }
  }

  override fun rxGetParent(): DocumentReference? {
    val parent = collectionDelegate.parent

    return if(parent == null) {
      null
    } else {
      DocumentReference.newInstance(parent)
    }
  }

  override fun rxGetFirestore(): FirebaseFirestore {
    return FirebaseFirestore.getInstance(collectionDelegate.firestore)
  }

  override fun rxLimit(limit: Long): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).limit(limit)

    return this
  }

  override fun rxOrderBy(field: String, direction: GoogleQuery.Direction): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).orderBy(field, direction)

    return this
  }

  override fun rxOrderBy(fieldPath: FieldPath): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).orderBy(fieldPath)

    return this
  }

  override fun rxOrderBy(fieldPath: FieldPath, direction: GoogleQuery.Direction): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).orderBy(fieldPath, direction)

    return this
  }

  override fun rxOrderBy(field: String): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).orderBy(field)

    return this
  }

  override fun rxStartAfter(vararg fieldValues: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).startAfter(fieldValues)

    return this
  }

  override fun rxStartAfter(snapshot: DocumentSnapshot): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).startAfter(snapshot)

    return this
  }

  override fun rxStartAt(vararg fieldValues: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).startAt(fieldValues)

    return this
  }

  override fun rxStartAt(snapshot: DocumentSnapshot): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).startAt(snapshot)

    return this
  }

  override fun rxWhereArrayContains(fieldPath: FieldPath, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereArrayContains(fieldPath, value)

    return this
  }

  override fun rxWhereArrayContains(field: String, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereArrayContains(field, value)

    return this
  }

  override fun rxWhereEqualTo(field: String, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereEqualTo(field, value)

    return this
  }

  override fun rxWhereEqualTo(fieldPath: FieldPath, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereEqualTo(fieldPath, value)

    return this
  }

  override fun rxWhereGreaterThan(fieldPath: FieldPath, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereGreaterThan(fieldPath, value)

    return this
  }

  override fun rxWhereGreaterThan(field: String, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereGreaterThan(field, value)

    return this
  }

  override fun rxWhereGreaterThanOrEqualTo(field: String, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereGreaterThanOrEqualTo(field, value)

    return this
  }

  override fun rxWhereGreaterThanOrEqualTo(fieldPath: FieldPath, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereGreaterThanOrEqualTo(fieldPath, value)

    return this
  }

  override fun rxWhereLessThan(fieldPath: FieldPath, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereLessThan(fieldPath, value)

    return this
  }

  override fun rxWhereLessThan(field: String, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereLessThan(field, value)

    return this
  }

  override fun rxWhereLessThanOrEqualTo(fieldPath: FieldPath, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereLessThanOrEqualTo(fieldPath, value)

    return this
  }

  override fun rxWhereLessThanOrEqualTo(field: String, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereLessThanOrEqualTo(field, value)

    return this
  }

  override fun startAfter(vararg fieldValues: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).startAfter(fieldValues)

    return queryDelegate!!
  }

  override fun startAfter(snapshot: DocumentSnapshot): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).startAfter(snapshot)

    return queryDelegate!!
  }

  override fun startAt(vararg fieldValues: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).startAt(fieldValues)

    return queryDelegate!!
  }

  override fun startAt(snapshot: DocumentSnapshot): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).startAt(snapshot)

    return queryDelegate!!
  }

  override fun whereArrayContains(fieldPath: FieldPath, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereArrayContains(fieldPath, value)

    return queryDelegate!!
  }

  override fun whereArrayContains(field: String, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereArrayContains(field, value)

    return queryDelegate!!
  }

  override fun whereEqualTo(field: String, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereEqualTo(field, value)

    return queryDelegate!!
  }

  override fun whereEqualTo(fieldPath: FieldPath, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereEqualTo(fieldPath, value)

    return queryDelegate!!
  }

  override fun whereGreaterThan(fieldPath: FieldPath, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereGreaterThan(fieldPath, value)

    return queryDelegate!!
  }

  override fun whereGreaterThan(field: String, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereGreaterThan(field, value)

    return queryDelegate!!
  }

  override fun whereGreaterThanOrEqualTo(field: String, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereGreaterThanOrEqualTo(field, value)

    return queryDelegate!!
  }

  override fun whereGreaterThanOrEqualTo(fieldPath: FieldPath, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereGreaterThanOrEqualTo(fieldPath, value)

    return queryDelegate!!
  }

  override fun whereLessThan(field: String, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereLessThan(field, value)

    return queryDelegate!!
  }

  override fun whereLessThan(fieldPath: FieldPath, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereLessThan(fieldPath, value)

    return queryDelegate!!
  }

  override fun whereLessThanOrEqualTo(fieldPath: FieldPath, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereLessThanOrEqualTo(fieldPath, value)

    return queryDelegate!!
  }

  override fun whereLessThanOrEqualTo(field: String, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate).whereLessThanOrEqualTo(field, value)

    return queryDelegate!!
  }
}