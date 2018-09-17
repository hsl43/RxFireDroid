package com.labs2160.rxfiredroid.database

import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.DataSnapshot as GoogleDataSnapshot
import com.google.firebase.database.DatabaseReference as GoogleDatabaseReference

interface DataSnapshot {
  companion object {
    fun newInstance(googleDataSnapshot: GoogleDataSnapshot): DataSnapshot {
      return DataSnapshotImpl(googleDataSnapshot)
    }
  }

  /**
   * Get a DataSnapshot for the location at the specified relative [path].
   */
  fun child(path: String): GoogleDataSnapshot

  /**
   * Returns true if the snapshot contains a non-null value.
   */
  fun exists(): Boolean

  /**
   * Gives access to all of the immediate children of this snapshot.
   */
  fun getChildren(): Iterable<GoogleDataSnapshot>

  fun getChildrenCount(): Long

  fun getKey(): String?

  /**
   * Returns the priority of the data contained in this snapshot as a native
   * type.
   */
  fun getPriority(): Any?

  /**
   * Used to obtain a reference to the source location for this snapshot.
   */
  fun getRef(): GoogleDatabaseReference

  /**
   * getValue() returns the data contained in this snapshot as native types.
   */
  fun getValue(useExportFormat: Boolean): Any?

  /**
   * Due to the way that Java implements generics, it takes an extra step to
   * get back a properly-typed Collection.
   */
  fun <T> getValue(t: GenericTypeIndicator<T>): T?

  /**
   * getValue() returns the data contained in this snapshot as native types.
   */
  fun getValue(): Any?

  /**
   * This method is used to marshall the data contained in this snapshot into a
   * class of your choosing.
   */
  fun <T> getValue(valueType: Class<T>): T?

  /**
   * Can be used to determine if this DataSnapshot has data at a particular
   * location.
   */
  fun hasChild(path: String): Boolean

  /**
   * Indicates whether this snapshot has any children.
   */
  fun hasChildren(): Boolean

  /**
   * Get a DataSnapshot for the location at the specified relative [path].
   */
  fun rxChild(path: String): DataSnapshot

  /**
   * Gives access to all of the immediate children of this snapshot.
   */
  fun rxGetChildren(): Iterable<DataSnapshot>

  /**
   * Used to obtain a reference to the source location for this snapshot.
   */
  fun rxGetRef(): DatabaseReference
}