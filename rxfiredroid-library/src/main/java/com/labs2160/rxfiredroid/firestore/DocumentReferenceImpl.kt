package com.labs2160.rxfiredroid.firestore

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import com.labs2160.rxfiredroid.addRxOnCompleteListener
import io.reactivex.*
import java.util.concurrent.Executor
import com.google.firebase.firestore.CollectionReference as GoogleCollectionReference
import com.google.firebase.firestore.DocumentReference as GoogleDocumentReference
import com.google.firebase.firestore.DocumentSnapshot as GoogleDocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore as GoogleFirebaseFirestore

@Suppress("unused")
internal class DocumentReferenceImpl(private val delegate: GoogleDocumentReference) : DocumentReference {
  override fun addSnapshotListener(
      activity: Activity,
      metadataChanges: MetadataChanges,
      listener: EventListener<GoogleDocumentSnapshot>

  ): ListenerRegistration {

    return delegate.addSnapshotListener(activity, metadataChanges, listener)
  }

  override fun addSnapshotListener(
      executor: Executor,
      metadataChanges: MetadataChanges,
      listener: EventListener<GoogleDocumentSnapshot>

  ): ListenerRegistration {

    return delegate.addSnapshotListener(executor, metadataChanges, listener)
  }

  override fun addSnapshotListener(
      metadataChanges: MetadataChanges,
      listener: EventListener<GoogleDocumentSnapshot>

  ): ListenerRegistration {

    return delegate.addSnapshotListener(metadataChanges, listener)
  }

  override fun addSnapshotListener(listener: EventListener<GoogleDocumentSnapshot>): ListenerRegistration {
    return delegate.addSnapshotListener(listener)
  }

  override fun addSnapshotListener(executor: Executor, listener: EventListener<GoogleDocumentSnapshot>): ListenerRegistration {
    return delegate.addSnapshotListener(executor, listener)
  }

  override fun addSnapshotListener(activity: Activity, listener: EventListener<GoogleDocumentSnapshot>): ListenerRegistration {
    return delegate.addSnapshotListener(activity, listener)
  }

  override fun collection(path: String): GoogleCollectionReference {
    return delegate.collection(path)
  }

  override fun delegate(): GoogleDocumentReference {
    return delegate
  }

  override fun delete(): Task<Void> {
    return delegate.delete()
  }

  override fun get(): Task<GoogleDocumentSnapshot> {
    return delegate.get()
  }

  override fun get(source: Source): Task<GoogleDocumentSnapshot> {
    return delegate.get(source)
  }

  override fun getFirestore(): GoogleFirebaseFirestore {
    return delegate.firestore
  }

  override fun getId(): String {
    return delegate.id
  }

  override fun getParent(): GoogleCollectionReference {
    return delegate.parent
  }

  override fun getPath(): String {
    return delegate.path
  }

  override fun rxBindSnapshot(
      activity: Activity,
      metadataChanges: MetadataChanges,
      backpressureStrategy: BackpressureStrategy

  ): Flowable<DocumentSnapshot> {

    val source = FlowableOnSubscribe<DocumentSnapshot> { emitter ->
     val listener = EventListener<GoogleDocumentSnapshot> { value, error ->
       if(value != null) {
         emitter.onNext(DocumentSnapshot.newInstance(value))
       } else if(error != null) {
         emitter.onError(error)
       }
     }

      delegate.addSnapshotListener(activity, metadataChanges, listener)
    }

    return Flowable.create(source, backpressureStrategy)
  }

  override fun rxBindSnapshot(
      executor: Executor,
      metadataChanges: MetadataChanges,
      backpressureStrategy: BackpressureStrategy

  ): Flowable<DocumentSnapshot> {

    val source = FlowableOnSubscribe<DocumentSnapshot> { emitter ->
      val listener = EventListener<GoogleDocumentSnapshot> { value, error ->
        if(value != null) {
          emitter.onNext(DocumentSnapshot.newInstance(value))
        } else if(error != null) {
          emitter.onError(error)
        }
      }

      delegate.addSnapshotListener(executor, metadataChanges, listener)
    }

    return Flowable.create(source, backpressureStrategy)
  }

  override fun rxBindSnapshot(metadataChanges: MetadataChanges, backpressureStrategy: BackpressureStrategy): Flowable<DocumentSnapshot> {
    val source = FlowableOnSubscribe<DocumentSnapshot> { emitter ->
      val listener = EventListener<GoogleDocumentSnapshot> { value, error ->
        if(value != null) {
          emitter.onNext(DocumentSnapshot.newInstance(value))
        } else if (error != null) {
          emitter.onError(error)
        }
      }

      delegate.addSnapshotListener(metadataChanges, listener)
    }

    return Flowable.create(source, backpressureStrategy)
  }

  override fun rxBindSnapshot(backpressureStrategy: BackpressureStrategy): Flowable<DocumentSnapshot> {
    val source = FlowableOnSubscribe<DocumentSnapshot> { emitter ->
      val listener = EventListener<GoogleDocumentSnapshot> { value, error ->
        if(value != null) {
          emitter.onNext(DocumentSnapshot.newInstance(value))
        } else if (error != null) {
          emitter.onError(error)
        }
      }

      delegate.addSnapshotListener(listener)
    }

    return Flowable.create(source, backpressureStrategy)
  }

  override fun rxBindSnapshot(executor: Executor, backpressureStrategy: BackpressureStrategy): Flowable<DocumentSnapshot> {
    val source = FlowableOnSubscribe<DocumentSnapshot> { emitter ->
      val listener = EventListener<GoogleDocumentSnapshot> { value, error ->
        if(value != null) {
          emitter.onNext(DocumentSnapshot.newInstance(value))
        } else if (error != null) {
          emitter.onError(error)
        }
      }

      delegate.addSnapshotListener(executor, listener)
    }

    return Flowable.create(source, backpressureStrategy)
  }

  override fun rxBindSnapshot(activity: Activity, backpressureStrategy: BackpressureStrategy): Flowable<DocumentSnapshot> {
    val source = FlowableOnSubscribe<DocumentSnapshot> { emitter ->
      val listener = EventListener<GoogleDocumentSnapshot> { value, error ->
        if(value != null) {
          emitter.onNext(DocumentSnapshot.newInstance(value))
        } else if (error != null) {
          emitter.onError(error)
        }
      }

      delegate.addSnapshotListener(activity, listener)
    }

    return Flowable.create(source, backpressureStrategy)
  }

  override fun rxCollection(path: String): CollectionReference {
    return QueryableCollectionReference.newInstance(delegate.collection(path))
  }

  override fun rxDelete(): Completable {
    return Completable.create { emitter ->
      delegate.delete()
          .addRxOnCompleteListener(emitter, "Unexpected error in rxDelete()")
    }
  }

  override fun rxGet(): Single<DocumentSnapshot> {
    return Single.create { emitter ->
      delegate.get()
          .addOnCompleteListener { task ->
            if(task.isSuccessful) {
              emitter.onSuccess(DocumentSnapshot.newInstance(task.result))
            } else {
              emitter.onError(task.exception ?: RuntimeException("Unexpected error in rxGet()"))
            }
          }
    }
  }

  override fun rxGet(source: Source): Single<DocumentSnapshot> {
    return Single.create { emitter ->
      delegate.get(source)
          .addOnCompleteListener { task ->
            if(task.isSuccessful) {
              emitter.onSuccess(DocumentSnapshot.newInstance(task.result))
            } else {
              emitter.onError(task.exception ?: RuntimeException("Unexpected error in rxGet()"))
            }
          }
    }
  }

  override fun rxGetFirestore(): FirebaseFirestore {
    return FirebaseFirestore.getInstance(delegate.firestore)
  }

  override fun rxGetParent(): CollectionReference {
    return QueryableCollectionReference.newInstance(delegate.parent)
  }

  override fun rxSet(pojo: Any): Completable {
    return Completable.create { emitter ->
      delegate.set(pojo)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxSet()")
    }
  }

  override fun rxSet(data: Map<String,Any>, options: SetOptions): Completable {
    return Completable.create { emitter ->
      delegate.set(data, options)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxSet()")
    }
  }

  override fun rxSet(pojo: Any, options: SetOptions): Completable {
    return Completable.create { emitter ->
      delegate.set(pojo, options)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxSet()")
    }
  }

  override fun rxSet(data: Map<String,Any>): Completable {
    return Completable.create { emitter ->
      delegate.set(data)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxSet()")
    }
  }

  override fun rxUpdate(data: Map<String,Any>): Completable {
    return Completable.create { emitter ->
      delegate.update(data)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxUpdate()")
    }
  }

  override fun rxUpdate(field: String, value: Any, vararg moreFieldsAndValues: Any): Completable {
    return Completable.create { emitter ->
      delegate.update(field, value, moreFieldsAndValues)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxUpdate()")
    }
  }

  override fun rxUpdate(fieldPath: FieldPath, value: Any, vararg moreFieldsAndValues: Any): Completable {
    return Completable.create { emitter ->
      delegate.update(fieldPath, value, moreFieldsAndValues)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxUpdate()")
    }
  }

  override fun set(pojo: Any): Task<Void> {
    return delegate.set(pojo)
  }

  override fun set(data: Map<String,Any>, options: SetOptions): Task<Void> {
    return delegate.set(data, options)
  }

  override fun set(pojo: Any, options: SetOptions): Task<Void> {
    return delegate.set(pojo, options)
  }

  override fun set(data: Map<String,Any>): Task<Void> {
    return delegate.set(data)
  }

  override fun update(data: Map<String,Any>): Task<Void> {
    return delegate.update(data)
  }

  override fun update(field: String, value: Any, vararg moreFieldsAndValues: Any): Task<Void> {
    return delegate.update(field, value, moreFieldsAndValues)
  }

  override fun update(fieldPath: FieldPath, value: Any, vararg moreFieldsAndValues: Any): Task<Void> {
    return delegate.update(fieldPath, value, moreFieldsAndValues)
  }
}