package com.labs2160.rxfiredroid.firestore

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import com.labs2160.rxfiredroid.Defaults
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import java.util.concurrent.Executor
import com.google.firebase.firestore.FirebaseFirestore as GoogleFirebaseFirestore
import com.google.firebase.firestore.Query as GoogleQuery
import com.google.firebase.firestore.QuerySnapshot as GoogleQuerySnapshot

interface Query {
  /**
   * Starts listening to this query using an Activity-scoped listener.
   */
  fun addSnapshotListener(activity: Activity, listener: EventListener<GoogleQuerySnapshot>): ListenerRegistration

  /**
   * Starts listening to this query with the given options.
   */
  fun addSnapshotListener(metadataChanges: MetadataChanges, listener: EventListener<GoogleQuerySnapshot>): ListenerRegistration

  /**
   * Starts listening to this query.
   */
  fun addSnapshotListener(executor: Executor, listener: EventListener<GoogleQuerySnapshot>): ListenerRegistration

  /**
   * Starts listening to this query with the given options.
   */
  fun addSnapshotListener(
      executor: Executor,
      metadataChanges: MetadataChanges,
      listener: EventListener<GoogleQuerySnapshot>

  ): ListenerRegistration

  /**
   * Starts listening to this query with the given options, using an
   * Activity-scoped listener.
   */
  fun addSnapshotListener(
      activity: Activity,
      metadataChanges: MetadataChanges,
      listener: EventListener<GoogleQuerySnapshot>

  ): ListenerRegistration

  /**
   * Starts listening to this query.
   */
  fun addSnapshotListener(listener: EventListener<GoogleQuerySnapshot>): ListenerRegistration

  /**
   * Creates and returns a new Query that ends at the provided document
   * (inclusive).
   */
  fun endAt(snapshot: DocumentSnapshot): GoogleQuery

  /**
   * Creates and returns a new Query that ends at the provided fields relative
   * to the order of the query.
   */
  fun endAt(vararg fieldValues: Any): GoogleQuery

  /**
   * Creates and returns a new Query that ends before the provided document
   * (exclusive).
   */
  fun endBefore(snapshot: DocumentSnapshot): GoogleQuery

  /**
   * Creates and returns a new Query that ends before the provided fields
   * relative to the order of the query.
   */
  fun endBefore(vararg fieldValues: Any): GoogleQuery

  /**
   * Executes the query and returns the results as a QuerySnapshot.
   */
  fun get(): Task<GoogleQuerySnapshot>

  /**
   * Executes the query and returns the results as a QuerySnapshot.
   */
  fun get(source: Source): Task<GoogleQuerySnapshot>

  /**
   * Gets the Firestore instance associated with this query.
   */
  fun getFirestore(): GoogleFirebaseFirestore

  /**
   * Creates and returns a new Query that's additionally limited to only return
   * up to the specified number of documents.
   */
  fun limit(limit: Long): GoogleQuery

  /**
   * Creates and returns a new Query that's additionally sorted by the
   * specified field, optionally in descending order instead of ascending.
   */
  fun orderBy(field: String, direction: GoogleQuery.Direction): GoogleQuery

  /**
   * Creates and returns a new Query that's additionally sorted by the
   * specified field.
   */
  fun orderBy(fieldPath: FieldPath): GoogleQuery

  /**
   * Creates and returns a new Query that's additionally sorted by the
   * specified field, optionally in descending order instead of ascending.
   */
  fun orderBy(fieldPath: FieldPath, direction: GoogleQuery.Direction): GoogleQuery

  /**
   * Creates and returns a new Query that's additionally sorted by the
   * specified field.
   */
  fun orderBy(field: String): GoogleQuery

  /**
   * Observe this query using an Activity-scoped listener.
   */
  fun rxBindSnapshot(activity: Activity): Flowable<QuerySnapshot> {
    return this.rxBindSnapshot(activity, Defaults.BACKPRESSURE_STRATEGY)
  }

  /**
   * Observe this query using an Activity-scoped listener.
   */
  fun rxBindSnapshot(activity: Activity, backpressureStrategy: BackpressureStrategy): Flowable<QuerySnapshot>

  /**
   * Observe this query with the given options.
   */
  fun rxBindSnapshot(metadataChanges: MetadataChanges): Flowable<QuerySnapshot> {
    return this.rxBindSnapshot(metadataChanges, Defaults.BACKPRESSURE_STRATEGY)
  }

  /**
   * Observe this query with the given options.
   */
  fun rxBindSnapshot(metadataChanges: MetadataChanges, backpressureStrategy: BackpressureStrategy): Flowable<QuerySnapshot>

  /**
   * Starts observing this query.
   */
  fun rxBindSnapshot(executor: Executor): Flowable<QuerySnapshot> {
    return this.rxBindSnapshot(executor, Defaults.BACKPRESSURE_STRATEGY)
  }

  /**
   * Starts observing this query.
   */
  fun rxBindSnapshot(executor: Executor, backpressureStrategy: BackpressureStrategy): Flowable<QuerySnapshot>

  /**
   * Starts observing this query with the given options.
   */
  fun rxBindSnapshot(executor: Executor, metadataChanges: MetadataChanges): Flowable<QuerySnapshot> {
    return this.rxBindSnapshot(executor, metadataChanges, Defaults.BACKPRESSURE_STRATEGY)
  }

  /**
   * Starts observing this query with the given options.
   */
  fun rxBindSnapshot(
      executor: Executor,
      metadataChanges: MetadataChanges,
      backpressureStrategy: BackpressureStrategy

  ): Flowable<QuerySnapshot>

  /**
   * Starts observing this query with the given options, using an
   * Activity-scoped listener.
   */
  fun rxBindSnapshot(activity: Activity, metadataChanges: MetadataChanges): Flowable<QuerySnapshot> {
    return this.rxBindSnapshot(activity, metadataChanges, Defaults.BACKPRESSURE_STRATEGY)
  }

  /**
   * Starts observing this query with the given options, using an
   * Activity-scoped listener.
   */
  fun rxBindSnapshot(
      activity: Activity,
      metadataChanges: MetadataChanges,
      backpressureStrategy: BackpressureStrategy

  ): Flowable<QuerySnapshot>

  /**
   * Starts observing this query.
   */
  fun rxBindSnapshot(): Flowable<QuerySnapshot> {
    return this.rxBindSnapshot(Defaults.BACKPRESSURE_STRATEGY)
  }

  /**
   * Starts observing this query.
   */
  fun rxBindSnapshot(backpressureStrategy: BackpressureStrategy): Flowable<QuerySnapshot>

  /**
   * Creates and returns a new Query that ends at the provided document
   * (inclusive).
   */
  fun rxEndAt(snapshot: DocumentSnapshot): Query

  /**
   * Creates and returns a new Query that ends at the provided fields relative
   * to the order of the query.
   */
  fun rxEndAt(vararg fieldValues: Any): Query

  /**
   * Creates and returns a new Query that ends before the provided document
   * (exclusive).
   */
  fun rxEndBefore(snapshot: DocumentSnapshot): Query

  /**
   * Creates and returns a new Query that ends before the provided fields
   * relative to the order of the query.
   */
  fun rxEndBefore(vararg fieldValues: Any): Query

  /**
   * Executes the query and returns the results as a QuerySnapshot.
   */
  fun rxGet(): Single<QuerySnapshot>

  /**
   * Executes the query and returns the results as a QuerySnapshot.
   */
  fun rxGet(source: Source): Single<QuerySnapshot>

  /**
   * Gets the Firestore instance associated with this query.
   */
  fun rxGetFirestore(): FirebaseFirestore

  /**
   * Creates and returns a new Query that's additionally limited to only return
   * up to the specified number of documents.
   */
  fun rxLimit(limit: Long): Query

  /**
   * Creates and returns a new Query that's additionally sorted by the
   * specified field, optionally in descending order instead of ascending.
   */
  fun rxOrderBy(field: String, direction: GoogleQuery.Direction): Query

  /**
   * Creates and returns a new Query that's additionally sorted by the
   * specified field.
   */
  fun rxOrderBy(fieldPath: FieldPath): Query

  /**
   * Creates and returns a new Query that's additionally sorted by the
   * specified field, optionally in descending order instead of ascending.
   */
  fun rxOrderBy(fieldPath: FieldPath, direction: GoogleQuery.Direction): Query

  /**
   * Creates and returns a new Query that's additionally sorted by the
   * specified field.
   */
  fun rxOrderBy(field: String): Query

  /**
   * Creates and returns a new Query that starts after the provided fields
   * relative to the order of the query.
   */
  fun rxStartAfter(vararg fieldValues: Any): Query

  /**
   * Creates and returns a new Query that starts after the provided document
   * (exclusive).
   */
  fun rxStartAfter(snapshot: DocumentSnapshot): Query

  /**
   * Creates and returns a new Query that starts at the provided fields
   * relative to the order of the query.
   */
  fun rxStartAt(vararg fieldValues: Any): Query

  /**
   * Creates and returns a new Query that starts at the provided document
   * (inclusive).
   */
  fun rxStartAt(snapshot: DocumentSnapshot): Query

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field, the value must be an array, and that the
   * array must contain the provided value.
   */
  fun rxWhereArrayContains(fieldPath: FieldPath, value: Any): Query

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field, the value must be an array, and that the
   * array must contain the provided value.
   */
  fun rxWhereArrayContains(field: String, value: Any): Query

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be equal to the
   * specified value.
   */
  fun rxWhereEqualTo(field: String, value: Any): Query

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be equal to the
   * specified value.
   */
  fun rxWhereEqualTo(fieldPath: FieldPath, value: Any): Query

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be greater than the
   * specified value.
   */
  fun rxWhereGreaterThan(fieldPath: FieldPath, value: Any): Query

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be greater than the
   * specified value.
   */
  fun rxWhereGreaterThan(field: String, value: Any): Query

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be less than the
   * specified value.
   */
  fun rxWhereLessThan(field: String, value: Any): Query

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be greater than or
   * equal to the specified value.
   */
  fun rxWhereGreaterThanOrEqualTo(field: String, value: Any): Query

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be greater than or
   * equal to the specified value.
   */
  fun rxWhereGreaterThanOrEqualTo(fieldPath: FieldPath, value: Any): Query

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be less than the
   * specified value.
   */
  fun rxWhereLessThan(fieldPath: FieldPath, value: Any): Query

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be less than or
   * equal to the specified value.
   */
  fun rxWhereLessThanOrEqualTo(fieldPath: FieldPath, value: Any): Query

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be less than or
   * equal to the specified value.
   */
  fun rxWhereLessThanOrEqualTo(field: String, value: Any): Query

  /**
   * Creates and returns a new Query that starts after the provided fields
   * relative to the order of the query.
   */
  fun startAfter(vararg fieldValues: Any): GoogleQuery

  /**
   * Creates and returns a new Query that starts after the provided document
   * (exclusive).
   */
  fun startAfter(snapshot: DocumentSnapshot): GoogleQuery

  /**
   * Creates and returns a new Query that starts at the provided fields
   * relative to the order of the query.
   */
  fun startAt(vararg fieldValues: Any): GoogleQuery

  /**
   * Creates and returns a new Query that starts at the provided document
   * (inclusive).
   */
  fun startAt(snapshot: DocumentSnapshot): GoogleQuery

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field, the value must be an array, and that the
   * array must contain the provided value.
   */
  fun whereArrayContains(fieldPath: FieldPath, value: Any): GoogleQuery

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field, the value must be an array, and that the
   * array must contain the provided value.
   */
  fun whereArrayContains(field: String, value: Any): GoogleQuery

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be equal to the
   * specified value.
   */
  fun whereEqualTo(field: String, value: Any): GoogleQuery

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be equal to the
   * specified value.
   */
  fun whereEqualTo(fieldPath: FieldPath, value: Any): GoogleQuery

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be greater than the
   * specified value.
   */
  fun whereGreaterThan(fieldPath: FieldPath, value: Any): GoogleQuery

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be greater than the
   * specified value.
   */
  fun whereGreaterThan(field: String, value: Any): GoogleQuery

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be greater than or
   * equal to the specified value.
   */
  fun whereGreaterThanOrEqualTo(field: String, value: Any): GoogleQuery

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be greater than or
   * equal to the specified value.
   */
  fun whereGreaterThanOrEqualTo(fieldPath: FieldPath, value: Any): GoogleQuery

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be less than the
   * specified value.
   */
  fun whereLessThan(field: String, value: Any): GoogleQuery

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be less than the
   * specified value.
   */
  fun whereLessThan(fieldPath: FieldPath, value: Any): GoogleQuery

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be less than or
   * equal to the specified value.
   */
  fun whereLessThanOrEqualTo(fieldPath: FieldPath, value: Any): GoogleQuery

  /**
   * Creates and returns a new Query with the additional filter that documents
   * must contain the specified field and the value should be less than or
   * equal to the specified value.
   */
  fun whereLessThanOrEqualTo(field: String, value: Any): GoogleQuery
}