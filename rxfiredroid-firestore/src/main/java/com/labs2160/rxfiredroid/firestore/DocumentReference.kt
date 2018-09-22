package com.labs2160.rxfiredroid.firestore

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import com.labs2160.rxfiredroid.Defaults
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import java.util.concurrent.Executor
import com.google.firebase.firestore.CollectionReference as GoogleCollectionReference
import com.google.firebase.firestore.DocumentReference as GoogleDocumentReference
import com.google.firebase.firestore.DocumentSnapshot as GoogleDocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore as GoogleFirebaseFirestore

interface DocumentReference {
  companion object {
    fun newInstance(googleDocumentReference: GoogleDocumentReference): DocumentReference {
      return DocumentReferenceImpl(googleDocumentReference)
    }
  }

  /**
   * Starts listening to the document referenced by this DocumentReference with
   * the given options using an Activity-scoped [listener].
   */
  fun addSnapshotListener(
      activity: Activity,
      metadataChanges: MetadataChanges,
      listener: EventListener<GoogleDocumentSnapshot>

  ): ListenerRegistration

  /**
   * Starts listening to the document referenced by this DocumentReference with
   * the given options.
   */
  fun addSnapshotListener(
      executor: Executor,
      metadataChanges: MetadataChanges,
      listener: EventListener<GoogleDocumentSnapshot>

  ): ListenerRegistration

  /**
   * Starts listening to the document referenced by this DocumentReference with
   * the given options.
   */
  fun addSnapshotListener(
      metadataChanges: MetadataChanges,
      listener: EventListener<GoogleDocumentSnapshot>

  ): ListenerRegistration

  /**
   * Starts listening to the document referenced by this DocumentReference.
   */
  fun addSnapshotListener(listener: EventListener<GoogleDocumentSnapshot>): ListenerRegistration

  /**
   * Starts listening to the document referenced by this DocumentReference.
   */
  fun addSnapshotListener(executor: Executor, listener: EventListener<GoogleDocumentSnapshot>): ListenerRegistration

  /**
   * Starts listening to the document referenced by this DocumentReference
   * using an Activity-scoped listener.
   */
  fun addSnapshotListener(activity: Activity, listener: EventListener<GoogleDocumentSnapshot>): ListenerRegistration

  /**
   * Gets a CollectionReference instance that refers to the sub-collection at
   * the specified [path] relative to this document.
   */
  fun collection(path: String): GoogleCollectionReference

  /**
   * For internal use in RxFireDroid only.
   */
  fun delegate(): GoogleDocumentReference

  /**
   * Deletes the document referred to by this DocumentReference.
   */
  fun delete(): Task<Void>

  /**
   * Reads the document referenced by this DocumentReference.
   */
  fun get(): Task<GoogleDocumentSnapshot>

  /**
   * Reads the document referenced by this DocumentReference.
   */
  fun get(source: Source): Task<GoogleDocumentSnapshot>

  /**
   * Gets the Firestore instance associated with this document reference.
   */
  fun getFirestore(): GoogleFirebaseFirestore

  fun getId(): String

  /**
   * Gets a CollectionReference to the collection that contains this document.
   */
  fun getParent(): GoogleCollectionReference

  /**
   * Gets the path of this document (relative to the root of the database) as a
   * slash-separated string.
   */
  fun getPath(): String

  /**
   * Starts observing the document referenced by this DocumentReference with
   * the given options using an Activity-scoped listener.
   */
  @Suppress("unused")
  fun rxBindSnapshot(activity: Activity, metadataChanges: MetadataChanges): Flowable<DocumentSnapshot> {
    return this.rxBindSnapshot(activity, metadataChanges, Defaults.BACKPRESSURE_STRATEGY)
  }

  /**
   * Starts observing the document referenced by this DocumentReference with
   * the given options using an Activity-scoped listener.
   */
  fun rxBindSnapshot(
      activity: Activity,
      metadataChanges: MetadataChanges,
      backpressureStrategy: BackpressureStrategy

  ): Flowable<DocumentSnapshot>

  /**
   * Starts observing the document referenced by this DocumentReference with
   * the given options.
   */
  @Suppress("unused")
  fun rxBindSnapshot(executor: Executor, metadataChanges: MetadataChanges): Flowable<DocumentSnapshot> {
    return this.rxBindSnapshot(executor, metadataChanges, Defaults.BACKPRESSURE_STRATEGY)
  }

  /**
   * Starts observing the document referenced by this DocumentReference with
   * the given options.
   */
  fun rxBindSnapshot(
      executor: Executor,
      metadataChanges: MetadataChanges,
      backpressureStrategy: BackpressureStrategy

  ): Flowable<DocumentSnapshot>

  /**
   * Starts observing the document referenced by this DocumentReference with
   * the given options.
   */
  @Suppress("unused")
  fun rxBindSnapshot(metadataChanges: MetadataChanges): Flowable<DocumentSnapshot> {
    return this.rxBindSnapshot(metadataChanges, Defaults.BACKPRESSURE_STRATEGY)
  }

  /**
   * Starts observing the document referenced by this DocumentReference with
   * the given options.
   */
  fun rxBindSnapshot(metadataChanges: MetadataChanges, backpressureStrategy: BackpressureStrategy): Flowable<DocumentSnapshot>

  /**
   * Starts observing the document referenced by this DocumentReference.
   */
  @Suppress("unused")
  fun rxBindSnapshot(): Flowable<DocumentSnapshot> {
    return this.rxBindSnapshot(Defaults.BACKPRESSURE_STRATEGY)
  }

  /**
   * Starts observing the document referenced by this DocumentReference.
   */
  fun rxBindSnapshot(backpressureStrategy: BackpressureStrategy): Flowable<DocumentSnapshot>

  /**
   * Starts observing the document referenced by this DocumentReference.
   */
  @Suppress("unused")
  fun rxBindSnapshot(executor: Executor): Flowable<DocumentSnapshot> {
    return this.rxBindSnapshot(executor, Defaults.BACKPRESSURE_STRATEGY)
  }

  /**
   * Starts observing the document referenced by this DocumentReference.
   */
  fun rxBindSnapshot(executor: Executor, backpressureStrategy: BackpressureStrategy): Flowable<DocumentSnapshot>

  /**
   * Starts observing the document referenced by this DocumentReference
   * using an Activity-scoped listener.
   */
  @Suppress("unused")
  fun rxBindSnapshot(activity: Activity): Flowable<DocumentSnapshot> {
    return this.rxBindSnapshot(activity, Defaults.BACKPRESSURE_STRATEGY)
  }

  /**
   * Starts observing the document referenced by this DocumentReference
   * using an Activity-scoped listener.
   */
  fun rxBindSnapshot(activity: Activity, backpressureStrategy: BackpressureStrategy): Flowable<DocumentSnapshot>

  /**
   * Gets a CollectionReference instance that refers to the sub-collection at
   * the specified [path] relative to this document.
   */
  fun rxCollection(path: String): CollectionReference

  /**
   * Deletes the document referred to by this DocumentReference.
   */
  fun rxDelete(): Completable

  /**
   * Reads the document referenced by this DocumentReference.
   */
  fun rxGet(): Single<DocumentSnapshot>

  /**
   * Reads the document referenced by this DocumentReference.
   */
  fun rxGet(source: Source): Single<DocumentSnapshot>

  /**
   * Gets the Firestore instance associated with this document reference.
   */
  fun rxGetFirestore(): FirebaseFirestore

  /**
   * Gets a CollectionReference to the collection that contains this document.
   */
  fun rxGetParent(): CollectionReference

  /**
   * Overwrites the document referred to by this DocumentReference.
   */
  fun rxSet(pojo: Any): Completable

  /**
   * Writes to the document referred to by this DocumentReference.
   */
  fun rxSet(data: Map<String,Any>, options: SetOptions): Completable

  /**
   * Writes to the document referred to by this DocumentReference.
   */
  fun rxSet(pojo: Any, options: SetOptions): Completable

  /**
   * Overwrites the document referred to by this DocumentReference.
   */
  fun rxSet(data: Map<String,Any>): Completable

  /**
   * Updates fields in the document referred to by this DocumentReference.
   */
  fun rxUpdate(data: Map<String,Any>): Completable

  /**
   * Updates fields in the document referred to by this DocumentReference.
   */
  fun rxUpdate(field: String, value: Any, vararg moreFieldsAndValues: Any): Completable

  /**
   * Updates fields in the document referred to by this DocumentReference.
   */
  fun rxUpdate(fieldPath: FieldPath, value: Any, vararg moreFieldsAndValues: Any): Completable

  /**
   * Overwrites the document referred to by this DocumentReference.
   */
  fun set(pojo: Any): Task<Void>

  /**
   * Writes to the document referred to by this DocumentReference.
   */
  fun set(data: Map<String,Any>, options: SetOptions): Task<Void>

  /**
   * Writes to the document referred to by this DocumentReference.
   */
  fun set(pojo: Any, options: SetOptions): Task<Void>

  /**
   * Overwrites the document referred to by this DocumentReference.
   */
  fun set(data: Map<String,Any>): Task<Void>

  /**
   * Updates fields in the document referred to by this DocumentReference.
   */
  fun update(data: Map<String,Any>): Task<Void>

  /**
   * Updates fields in the document referred to by this DocumentReference.
   */
  fun update(field: String, value: Any, vararg moreFieldsAndValues: Any): Task<Void>

  /**
   * Updates fields in the document referred to by this DocumentReference.
   */
  fun update(fieldPath: FieldPath, value: Any, vararg moreFieldsAndValues: Any): Task<Void>
}