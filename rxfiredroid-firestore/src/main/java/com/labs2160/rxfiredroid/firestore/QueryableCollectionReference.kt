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
internal class QueryableCollectionReference(
    private var collectionDelegate: GoogleCollectionReference? = null,
    private var queryDelegate: GoogleQuery? = null

) : CollectionReference {

  companion object {
    fun newInstance(googleCollectionReference: GoogleCollectionReference): CollectionReference {
      return QueryableCollectionReference(collectionDelegate = googleCollectionReference, queryDelegate = null)
    }

    fun newInstance(googleQuery: GoogleQuery): CollectionReference {
      return QueryableCollectionReference(collectionDelegate = null, queryDelegate = googleQuery)
    }

    fun newInstance(googleCollectionReference: GoogleCollectionReference, googleQuery: GoogleQuery): CollectionReference {
      return QueryableCollectionReference(collectionDelegate = googleCollectionReference, queryDelegate = googleQuery)
    }
  }

  override fun add(data: Map<String,Any>): Task<GoogleDocumentReference> {
    return collectionDelegate?.add(data)
        ?: throw IllegalStateException("add() was invoked without a backing CollectionReference")
  }

  override fun add(pojo: Any): Task<GoogleDocumentReference> {
    return collectionDelegate?.add(pojo)
        ?: throw IllegalStateException("add() was invoked without a backing CollectionReference")
  }

  override fun document(): GoogleDocumentReference {
    return collectionDelegate?.document()
        ?: throw IllegalStateException("document() was invoked without a backing CollectionReference")
  }

  override fun document(path: String): GoogleDocumentReference {
    return collectionDelegate?.document(path)
        ?: throw IllegalStateException("document() was invoked without a backing CollectionReference")
  }

  override fun getId(): String {
    return collectionDelegate?.id
        ?: throw IllegalStateException("getId() was invoked without a backing CollectionReference")
  }

  override fun getParent(): GoogleDocumentReference? {
    return collectionDelegate?.parent
        ?: throw IllegalStateException("getParent() was invoked without a backing CollectionReference")
  }

  override fun getPath(): String {
    return collectionDelegate?.path
        ?: throw IllegalStateException("getPath() was invoked without a backing CollectionReference")
  }

  override fun addSnapshotListener(activity: Activity, listener: EventListener<GoogleQuerySnapshot>): ListenerRegistration {
    return queryDelegate?.addSnapshotListener(activity, listener)
        ?: throw IllegalStateException("addSnapshotListener() was invoked without a backing Query")
  }

  override fun addSnapshotListener(metadataChanges: MetadataChanges, listener: EventListener<GoogleQuerySnapshot>): ListenerRegistration {
    return queryDelegate?.addSnapshotListener(metadataChanges, listener)
        ?: throw IllegalStateException("addSnapshotListener() was invoked without a backing Query")
  }

  override fun addSnapshotListener(executor: Executor, listener: EventListener<GoogleQuerySnapshot>): ListenerRegistration {
    return queryDelegate?.addSnapshotListener(executor, listener)
        ?: throw IllegalStateException("addSnapshotListener() was invoked without a backing Query")
  }

  override fun addSnapshotListener(
      executor: Executor,
      metadataChanges: MetadataChanges,
      listener: EventListener<GoogleQuerySnapshot>

  ): ListenerRegistration {

    return queryDelegate?.addSnapshotListener(executor, metadataChanges, listener)
        ?: throw IllegalStateException("addSnapshotListener() was invoked without a backing Query")
  }

  override fun addSnapshotListener(
      activity: Activity,
      metadataChanges: MetadataChanges,
      listener: EventListener<GoogleQuerySnapshot>

  ): ListenerRegistration {

    return queryDelegate?.addSnapshotListener(activity, metadataChanges, listener)
        ?: throw IllegalStateException("addSnapshotListener() was invoked without a backing Query")
  }

  override fun addSnapshotListener(listener: EventListener<GoogleQuerySnapshot>): ListenerRegistration {
    return queryDelegate?.addSnapshotListener(listener)
        ?: throw IllegalStateException("addSnapshotListener() was invoked without a backing Query")
  }

  override fun endAt(snapshot: DocumentSnapshot): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.endAt(snapshot)
        ?: throw IllegalStateException("endAt() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun endAt(vararg fieldValues: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.endAt(fieldValues)
        ?: throw IllegalStateException("endAt() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun endBefore(snapshot: DocumentSnapshot): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.endBefore(snapshot)
        ?: throw IllegalStateException("endBefore() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun endBefore(vararg fieldValues: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.endBefore(fieldValues)
        ?: throw IllegalStateException("endBefore() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun get(): Task<GoogleQuerySnapshot> {
    return (queryDelegate ?: collectionDelegate)?.get()
        ?: throw IllegalStateException("get() was invoked without a backing delegate")
  }

  override fun get(source: Source): Task<GoogleQuerySnapshot> {
    return (queryDelegate ?: collectionDelegate)?.get(source)
        ?: throw IllegalStateException("get() was invoked without a backing delegate")
  }

  override fun getFirestore(): GoogleFirebaseFirestore {
    return (queryDelegate ?: collectionDelegate)?.firestore
        ?: throw IllegalStateException("getFirestore() was invoked without a backing delegate")
  }

  override fun limit(limit: Long): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.limit(limit)
        ?: throw IllegalStateException("limit() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun orderBy(field: String, direction: GoogleQuery.Direction): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.orderBy(field, direction)
        ?: throw IllegalStateException("orderBy() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun orderBy(fieldPath: FieldPath): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.orderBy(fieldPath)
        ?: throw IllegalStateException("orderBy() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun orderBy(fieldPath: FieldPath, direction: GoogleQuery.Direction): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.orderBy(fieldPath, direction)
        ?: throw IllegalStateException("orderBy() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun orderBy(field: String): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.orderBy(field)
        ?: throw IllegalStateException("orderBy() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun rxAdd(data: Map<String,Any>): Single<DocumentReference> {
    val delegate = collectionDelegate ?: throw IllegalStateException("rxAdd() was invoked without a backing CollectionReference")

    return Single.create { emitter ->
      delegate.add(data)
          .addOnCompleteListener { task ->
            if(task.isSuccessful) {
              val result = task.result

              if(result != null) {
                emitter.onSuccess(DocumentReference.newInstance(result))
              } else {
                emitter.onError(RuntimeException("Task completed but did not produce a result"))
              }

            } else {
              emitter.onError(task.exception ?: RuntimeException("Unexpected error in rxAdd()"))
            }
          }
    }
  }

  override fun rxAdd(pojo: Any): Single<DocumentReference> {
    val delegate = collectionDelegate ?: throw IllegalStateException("rxAdd() was invoked without a backing CollectionReference")

    return Single.create { emitter ->
      delegate.add(pojo)
          .addOnCompleteListener { task ->
            if(task.isSuccessful) {
              val result = task.result

              if(result != null) {
                emitter.onSuccess(DocumentReference.newInstance(result))
              } else {
                emitter.onError(RuntimeException("Task completed but did not produce a result"))
              }

            } else {
              emitter.onError(task.exception ?: RuntimeException("Unexpected error in rxAdd()"))
            }
          }
    }
  }

  override fun rxBindSnapshot(activity: Activity, backpressureStrategy: BackpressureStrategy): Flowable<QuerySnapshot> {
    val delegate = queryDelegate ?: throw IllegalStateException("rxBindSnapshot() was invoked without a backing Query")

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
    val delegate = queryDelegate ?: throw IllegalStateException("rxBindSnapshot() was invoked without a backing Query")

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
    val delegate = queryDelegate ?: throw IllegalStateException("rxBindSnapshot() was invoked without a backing Query")

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

    val delegate = queryDelegate ?: throw IllegalStateException("rxBindSnapshot() was invoked without a backing Query")

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

    val delegate = queryDelegate ?: throw IllegalStateException("rxBindSnapshot() was invoked without a backing Query")

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
    val delegate = queryDelegate ?: throw IllegalStateException("rxBindSnapshot() was invoked without a backing Query")

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
    val delegate = collectionDelegate
        ?: throw IllegalStateException("rxBindSnapshot() was invoked without a backing CollectionReference")

    return DocumentReference.newInstance(delegate.document())
  }

  override fun rxDocument(path: String): DocumentReference {
    val delegate = collectionDelegate
        ?: throw IllegalStateException("rxBindSnapshot() was invoked without a backing CollectionReference")

    return DocumentReference.newInstance(delegate.document(path))
  }

  override fun rxEndAt(snapshot: DocumentSnapshot): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.endAt(snapshot)
        ?: throw IllegalStateException("rxEndAt() was invoked without a backing delegate")

    return this
  }

  override fun rxEndAt(vararg fieldValues: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.endAt(fieldValues)
        ?: throw IllegalStateException("rxEndAt() was invoked without a backing delegate")

    return this
  }

  override fun rxEndBefore(snapshot: DocumentSnapshot): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.endBefore(snapshot)
        ?: throw IllegalStateException("rxEndBefore() was invoked without a backing delegate")

    return this
  }

  override fun rxEndBefore(vararg fieldValues: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.endBefore(fieldValues)
        ?: throw IllegalStateException("rxEndBefore() was invoked without a backing delegate")

    return this
  }

  override fun rxGet(): Single<QuerySnapshot> {
    val delegate = (queryDelegate ?: collectionDelegate)
        ?: throw IllegalStateException("rxGet() was invoked without a backing delegate")

    return Single.create { emitter ->
      delegate.get()
          .addOnCompleteListener { task ->
            if(task.isSuccessful) {
              val result = task.result

              if(result != null) {
                emitter.onSuccess(QuerySnapshot.newInstance(result))
              } else {
                emitter.onError(RuntimeException("Task completed but did not produce a result"))
              }

            } else {
              emitter.onError(task.exception ?: RuntimeException("Unexpected error in rxGet()"))
            }
          }
    }
  }

  override fun rxGet(source: Source): Single<QuerySnapshot> {
    val delegate = (queryDelegate ?: collectionDelegate)
        ?: throw IllegalStateException("rxGet() was invoked without a backing delegate")

    return Single.create { emitter ->
      delegate.get(source)
          .addOnCompleteListener { task ->
            if(task.isSuccessful) {
              val result = task.result

              if(result != null) {
                emitter.onSuccess(QuerySnapshot.newInstance(result))
              } else {
                emitter.onError(RuntimeException("Task completed but did not produce a result"))
              }

            } else {
              emitter.onError(task.exception ?: RuntimeException("Unexpected error in rxGet()"))
            }
          }
    }
  }

  override fun rxGetParent(): DocumentReference? {
    val delegate = collectionDelegate
        ?: throw IllegalStateException("rxGetParent() was invoked without a backing CollectionReference")

    val parent = delegate.parent

    return if(parent == null) {
      null
    } else {
      DocumentReference.newInstance(parent)
    }
  }

  override fun rxGetFirestore(): FirebaseFirestore {
    val delegate = (queryDelegate ?: collectionDelegate)
        ?: throw IllegalStateException("rxGetFirestore() was invoked without a backing delegate")

    return FirebaseFirestore.getInstance(delegate.firestore)
  }

  override fun rxLimit(limit: Long): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.limit(limit)
        ?: throw IllegalStateException("rxLimit() was invoked without a backing delegate")

    return this
  }

  override fun rxOrderBy(field: String, direction: GoogleQuery.Direction): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.orderBy(field, direction)
        ?: throw IllegalStateException("rxOrderBy() was invoked without a backing delegate")

    return this
  }

  override fun rxOrderBy(fieldPath: FieldPath): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.orderBy(fieldPath)
        ?: throw IllegalStateException("rxOrderBy() was invoked without a backing delegate")

    return this
  }

  override fun rxOrderBy(fieldPath: FieldPath, direction: GoogleQuery.Direction): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.orderBy(fieldPath, direction)
        ?: throw IllegalStateException("rxOrderBy() was invoked without a backing delegate")

    return this
  }

  override fun rxOrderBy(field: String): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.orderBy(field)
        ?: throw IllegalStateException("rxOrderBy() was invoked without a backing delegate")

    return this
  }

  override fun rxStartAfter(vararg fieldValues: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.startAfter(fieldValues)
        ?: throw IllegalStateException("rxStartAfter() was invoked without a backing delegate")

    return this
  }

  override fun rxStartAfter(snapshot: DocumentSnapshot): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.startAfter(snapshot)
        ?: throw IllegalStateException("rxStartAfter() was invoked without a backing delegate")

    return this
  }

  override fun rxStartAt(vararg fieldValues: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.startAt(fieldValues)
        ?: throw IllegalStateException("rxStartAt() was invoked without a backing delegate")

    return this
  }

  override fun rxStartAt(snapshot: DocumentSnapshot): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.startAt(snapshot)
        ?: throw IllegalStateException("rxStartAt() was invoked without a backing delegate")

    return this
  }

  override fun rxWhereArrayContains(fieldPath: FieldPath, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereArrayContains(fieldPath, value)
        ?: throw IllegalStateException("rxWhereArrayContains() was invoked without a backing delegate")

    return this
  }

  override fun rxWhereArrayContains(field: String, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereArrayContains(field, value)
        ?: throw IllegalStateException("rxWhereArrayContains() was invoked without a backing delegate")

    return this
  }

  override fun rxWhereEqualTo(field: String, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereEqualTo(field, value)
        ?: throw IllegalStateException("rxWhereEqualTo() was invoked without a backing delegate")

    return this
  }

  override fun rxWhereEqualTo(fieldPath: FieldPath, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereEqualTo(fieldPath, value)
        ?: throw IllegalStateException("rxWhereEqualTo() was invoked without a backing delegate")

    return this
  }

  override fun rxWhereGreaterThan(fieldPath: FieldPath, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereGreaterThan(fieldPath, value)
        ?: throw IllegalStateException("rxWhereGreaterThan() was invoked without a backing delegate")

    return this
  }

  override fun rxWhereGreaterThan(field: String, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereGreaterThan(field, value)
        ?: throw IllegalStateException("rxWhereGreaterThan() was invoked without a backing delegate")

    return this
  }

  override fun rxWhereGreaterThanOrEqualTo(field: String, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereGreaterThanOrEqualTo(field, value)
        ?: throw IllegalStateException("rxWhereGreaterThanOrEqualTo() was invoked without a backing delegate")

    return this
  }

  override fun rxWhereGreaterThanOrEqualTo(fieldPath: FieldPath, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereGreaterThanOrEqualTo(fieldPath, value)
        ?: throw IllegalStateException("rxWhereGreaterThanOrEqualTo() was invoked without a backing delegate")

    return this
  }

  override fun rxWhereLessThan(fieldPath: FieldPath, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereLessThan(fieldPath, value)
        ?: throw IllegalStateException("rxWhereLessThan() was invoked without a backing delegate")

    return this
  }

  override fun rxWhereLessThan(field: String, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereLessThan(field, value)
        ?: throw IllegalStateException("rxWhereLessThan() was invoked without a backing delegate")

    return this
  }

  override fun rxWhereLessThanOrEqualTo(fieldPath: FieldPath, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereLessThanOrEqualTo(fieldPath, value)
        ?: throw IllegalStateException("rxWhereLessThanOrEqualTo() was invoked without a backing delegate")

    return this
  }

  override fun rxWhereLessThanOrEqualTo(field: String, value: Any): Query {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereLessThanOrEqualTo(field, value)
        ?: throw IllegalStateException("rxWhereLessThanOrEqualTo() was invoked without a backing delegate")

    return this
  }

  override fun startAfter(vararg fieldValues: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.startAfter(fieldValues)
        ?: throw IllegalStateException("startAfter() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun startAfter(snapshot: DocumentSnapshot): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.startAfter(snapshot)
        ?: throw IllegalStateException("startAfter() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun startAt(vararg fieldValues: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.startAt(fieldValues)
        ?: throw IllegalStateException("startAt() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun startAt(snapshot: DocumentSnapshot): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.startAt(snapshot)
        ?: throw IllegalStateException("startAt() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun whereArrayContains(fieldPath: FieldPath, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereArrayContains(fieldPath, value)
        ?: throw IllegalStateException("whereArrayContains() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun whereArrayContains(field: String, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereArrayContains(field, value)
        ?: throw IllegalStateException("whereArrayContains() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun whereEqualTo(field: String, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereEqualTo(field, value)
        ?: throw IllegalStateException("whereEqualTo() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun whereEqualTo(fieldPath: FieldPath, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereEqualTo(fieldPath, value)
        ?: throw IllegalStateException("whereEqualTo() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun whereGreaterThan(fieldPath: FieldPath, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereGreaterThan(fieldPath, value)
        ?: throw IllegalStateException("whereGreaterThan() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun whereGreaterThan(field: String, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereGreaterThan(field, value)
        ?: throw IllegalStateException("whereGreaterThan() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun whereGreaterThanOrEqualTo(field: String, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereGreaterThanOrEqualTo(field, value)
        ?: throw IllegalStateException("whereGreaterThanOrEqualTo() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun whereGreaterThanOrEqualTo(fieldPath: FieldPath, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereGreaterThanOrEqualTo(fieldPath, value)
        ?: throw IllegalStateException("whereGreaterThanOrEqualTo() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun whereLessThan(field: String, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereLessThan(field, value)
        ?: throw IllegalStateException("whereLessThan() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun whereLessThan(fieldPath: FieldPath, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereLessThan(fieldPath, value)
        ?: throw IllegalStateException("whereLessThan() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun whereLessThanOrEqualTo(fieldPath: FieldPath, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereLessThanOrEqualTo(fieldPath, value)
        ?: throw IllegalStateException("whereLessThanOrEqualTo() was invoked without a backing delegate")

    return queryDelegate!!
  }

  override fun whereLessThanOrEqualTo(field: String, value: Any): GoogleQuery {
    queryDelegate = (queryDelegate ?: collectionDelegate)?.whereLessThanOrEqualTo(field, value)
        ?: throw IllegalStateException("whereLessThanOrEqualTo() was invoked without a backing delegate")

    return queryDelegate!!
  }
}