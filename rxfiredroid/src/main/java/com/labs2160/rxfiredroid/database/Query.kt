package com.labs2160.rxfiredroid.database

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.ValueEventListener
import com.labs2160.rxfiredroid.Defaults
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import com.google.firebase.database.DatabaseReference as GoogleDatabaseReference
import com.google.firebase.database.Query as GoogleQuery

interface Query {
  companion object {
    fun newInstance(googleQuery: GoogleQuery): Query {
      return QueryImpl(googleQuery)
    }
  }

  /**
   * Add a listener for child events occurring at this location.
   */
  fun addChildEventListener(listener: ChildEventListener): ChildEventListener

  /**
   * Add a listener for a single change in the data at this location.
   */
  fun addListenerForSingleValueEvent(listener: ValueEventListener)

  /**
   * Add a listener for changes in the data at this location.
   */
  fun addValueEventListener(listener: ValueEventListener): ValueEventListener

  /**
   * Create a query constrained to only return child nodes with a value less
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default, and additionally only child nodes with a key less
   * than or equal to the given [key].
   */
  fun endAt(value: String, key: String): GoogleQuery

  /**
   * Create a query constrained to only return child nodes with a value less
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default.
   */
  fun endAt(value: String): GoogleQuery

  /**
   * Create a query constrained to only return child nodes with a value less
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default, and additionally only child nodes with a key less
   * than or equal to the given [key].
   */
  fun endAt(value: Double, key: String): GoogleQuery

  /**
   * Create a query constrained to only return child nodes with a value less
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default.
   */
  fun endAt(value: Double): GoogleQuery

  /**
   * Create a query constrained to only return child nodes with a value less
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default.
   */
  fun endAt(value: Boolean): GoogleQuery

  /**
   * Create a query constrained to only return child nodes with a value less
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default, and additionally only child nodes with a key less
   * than or equal to the given [key].
   */
  fun endAt(value: Boolean, key: String): GoogleQuery

  /**
   * Create a query constrained to only return the child node with the given
   * [key] and [value].
   */
  fun equalTo(value: String, key: String): GoogleQuery

  /**
   * Create a query constrained to only return child nodes with the given
   * [value].
   */
  fun equalTo(value: Double): GoogleQuery

  /**
   * Create a query constrained to only return the child node with the given
   * [key] and [value].
   */
  fun equalTo(value: Double, key: String): GoogleQuery

  /**
   * Create a query constrained to only return child nodes with the given
   * [value].
   */
  fun equalTo(value: String): GoogleQuery

  /**
   * Create a query constrained to only return the child node with the given
   * key and value.
   */
  fun equalTo(value: Boolean, key: String): GoogleQuery

  /**
   * Create a query constrained to only return child nodes with the given
   * [value].
   */
  fun equalTo(value: Boolean): GoogleQuery

  fun getRef(): GoogleDatabaseReference

  /**
   * By calling `keepSynced(true)` on a location, the data for that location
   * will automatically be downloaded and kept in sync, even when no listeners
   * are attached for that location.
   */
  fun keepSynced(keepSynced: Boolean)

  /**
   * Create a query with [limit] and anchor it to the start of the window.
   */
  fun limitToFirst(limit: Int): GoogleQuery

  /**
   * Create a query with [limit] and anchor it to the end of the window.
   */
  fun limitToLast(limit: Int): GoogleQuery

  /**
   * Create a query in which child nodes are ordered by the values of the
   * specified [path].
   */
  fun orderByChild(path: String): GoogleQuery

  /**
   * Create a query in which child nodes are ordered by their keys.
   */
  fun orderByKey(): GoogleQuery

  /**
   * Create a query in which child nodes are ordered by their priorities.
   */
  fun orderByPriority(): GoogleQuery

  /**
   * Create a query in which nodes are ordered by their value
   */
  fun orderByValue(): GoogleQuery

  /**
   * Remove the specified listener from this location.
   */
  fun removeEventListener(listener: ChildEventListener)

  /**
   * Remove the specified listener from this location.
   */
  fun removeEventListener(listener: ValueEventListener)

  /**
   * Observe child events occurring at this location.
   */
  fun rxBindChildEvents(): Flowable<ChildDataSnapshotState> {
    return this.rxBindChildEvents(Defaults.BACKPRESSURE_STRATEGY)
  }

  /**
   * Observe child events occurring at this location.
   */
  fun rxBindChildEvents(backpressureStrategy: BackpressureStrategy): Flowable<ChildDataSnapshotState>

  /**
   * Observe a single change in the data at this location.
   */
  fun rxBindSingleValueEvent(): Single<DataSnapshot>

  /**
   * Observe changes in the data at this location.
   */
  fun rxBindValueEvent(): Flowable<DataSnapshot> {
    return this.rxBindValueEvent(Defaults.BACKPRESSURE_STRATEGY)
  }

  /**
   * Observe changes in the data at this location.
   */
  fun rxBindValueEvent(backpressureStrategy: BackpressureStrategy): Flowable<DataSnapshot>

  /**
   * Create a query constrained to only return child nodes with a value less
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default, and additionally only child nodes with a key less
   * than or equal to the given [key].
   */
  fun rxEndAt(value: String, key: String): Query

  /**
   * Create a query constrained to only return child nodes with a value less
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default.
   */
  fun rxEndAt(value: String): Query

  /**
   * Create a query constrained to only return child nodes with a value less
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default, and additionally only child nodes with a key less
   * than or equal to the given [key].
   */
  fun rxEndAt(value: Double, key: String): Query

  /**
   * Create a query constrained to only return child nodes with a value less
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default.
   */
  fun rxEndAt(value: Double): Query

  /**
   * Create a query constrained to only return child nodes with a value less
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default.
   */
  fun rxEndAt(value: Boolean): Query

  /**
   * Create a query constrained to only return child nodes with a value less
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default, and additionally only child nodes with a key less
   * than or equal to the given [key].
   */
  fun rxEndAt(value: Boolean, key: String): Query

  /**
   * Create a query constrained to only return the child node with the given
   * [key] and [value].
   */
  fun rxEqualTo(value: String, key: String): Query

  /**
   * Create a query constrained to only return child nodes with the given
   * [value].
   */
  fun rxEqualTo(value: Double): Query

  /**
   * Create a query constrained to only return the child node with the given
   * [key] and [value].
   */
  fun rxEqualTo(value: Double, key: String): Query

  /**
   * Create a query constrained to only return child nodes with the given
   * [value].
   */
  fun rxEqualTo(value: String): Query

  /**
   * Create a query constrained to only return the child node with the given
   * [key] and [value].
   */
  fun rxEqualTo(value: Boolean, key: String): Query

  /**
   * Create a query constrained to only return child nodes with the given
   * [value].
   */
  fun rxEqualTo(value: Boolean): Query

  fun rxGetRef(): DatabaseReference

  /**
   * Create a query with [limit] and anchor it to the start of the window
   */
  fun rxLimitToFirst(limit: Int): Query

  /**
   * Create a query with [limit] and anchor it to the end of the window
   */
  fun rxLimitToLast(limit: Int): Query

  /**
   * Create a query in which child nodes are ordered by the values of the
   * specified [path].
   */
  fun rxOrderByChild(path: String): Query

  /**
   * Create a query in which child nodes are ordered by their keys.
   */
  fun rxOrderByKey(): Query

  /**
   * Create a query in which child nodes are ordered by their priorities.
   */
  fun rxOrderByPriority(): Query

  /**
   * Create a query in which nodes are ordered by their value
   */
  fun rxOrderByValue(): Query

  /**
   * Create a query constrained to only return child nodes with a value greater
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default.
   */
  fun rxStartAt(value: Double): Query

  /**
   * Create a query constrained to only return child nodes with a value greater
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default, and additionally only child nodes with a key greater
   * than or equal to the given [key].
   */
  fun rxStartAt(value: String, key: String): Query

  /**
   * Create a query constrained to only return child nodes with a value greater
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default.
   */
  fun rxStartAt(value: String): Query

  /**
   * Create a query constrained to only return child nodes with a value greater
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default, and additionally only child nodes with a key greater
   * than or equal to the given [key].
   */
  fun rxStartAt(value: Double, key: String): Query

  /**
   * Create a query constrained to only return child nodes with a value greater
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default.
   */
  fun rxStartAt(value: Boolean): Query

  /**
   * Create a query constrained to only return child nodes with a value greater
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default, and additionally only child nodes with a key greater
   * than or equal to the given [key].
   */
  fun rxStartAt(value: Boolean, key: String): Query

  /**
   * Create a query constrained to only return child nodes with a value greater
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default.
   */
  fun startAt(value: Double): GoogleQuery

  /**
   * Create a query constrained to only return child nodes with a value greater
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default, and additionally only child nodes with a key greater
   * than or equal to the given [key].
   */
  fun startAt(value: String, key: String): GoogleQuery

  /**
   * Create a query constrained to only return child nodes with a value greater
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default.
   */
  fun startAt(value: String): GoogleQuery

  /**
   * Create a query constrained to only return child nodes with a value greater
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default, and additionally only child nodes with a key greater
   * than or equal to the given [key].
   */
  fun startAt(value: Double, key: String): GoogleQuery

  /**
   * Create a query constrained to only return child nodes with a value greater
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default.
   */
  fun startAt(value: Boolean): GoogleQuery

  /**
   * Create a query constrained to only return child nodes with a value greater
   * than or equal to the given [value], using the given orderBy directive or
   * priority as default, and additionally only child nodes with a key greater
   * than or equal to the given [key].
   */
  fun startAt(value: Boolean, key: String): GoogleQuery
}