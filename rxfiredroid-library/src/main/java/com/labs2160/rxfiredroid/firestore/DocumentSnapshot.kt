package com.labs2160.rxfiredroid.firestore

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.SnapshotMetadata
import java.util.*
import com.google.firebase.firestore.DocumentReference as GoogleDocumentReference
import com.google.firebase.firestore.DocumentSnapshot as GoogleDocumentSnapshot

interface DocumentSnapshot {
  companion object {
    fun newInstance(googleDocumentSnapshot: GoogleDocumentSnapshot): DocumentSnapshot {
      return DocumentSnapshotImpl(googleDocumentSnapshot)
    }
  }

  /**
   * Returns whether or not the field exists in the document.
   */
  fun contains(fieldPath: FieldPath): Boolean

  /**
   * Returns whether or not the [field] exists in the document.
   */
  fun contains(field: String): Boolean

  fun exists(): Boolean

  /**
   * Returns the value at the [field] or null if the field doesn't exist.
   */
  fun get(field: String, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): Any?

  /**
   * Returns the value at the field or null if the field or document doesn't
   * exist.
   */
  fun get(fieldPath: FieldPath): Any?

  /**
   * Returns the value at the field or null if the field or document doesn't
   * exist.
   */
  fun get(fieldPath: FieldPath, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): Any?

  /**
   * Returns the value at the [field] or null if the field doesn't exist.
   */
  fun get(field: String): Any?

  /**
   * Returns the value of the [field] as a Blob.
   */
  fun getBlob(field: String): Blob?

  /**
   * Returns the value of the [field] as a boolean.
   */
  fun getBoolean(field: String): Boolean?

  /**
   * Returns the fields of the document as a Map or null if the document
   * doesn't exist.
   */
  fun getData(serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): Map<String,Any>?

  /**
   * Returns the fields of the document as a Map or null if the document
   * doesn't exist.
   */
  fun getData(): Map<String,Any>?

  /**
   * Returns the value of the [field] as a Date.
   */
  fun getDate(field: String): Date?

  /**
   * Returns the value of the [field] as a Date.
   */
  fun getDate(field: String, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): Date?

  /**
   * Returns the value of the [field] as a DocumentReference.
   */
  fun getDocumentReference(field: String): GoogleDocumentReference?

  /**
   * Returns the value of the [field] as a double.
   */
  fun getDouble(field: String): Double?

  /**
   * Returns the value of the [field] as a GeoPoint.
   */
  fun getGeoPoint(field: String): GeoPoint?

  fun getId(): String

  /**
   * Returns the value of the [field] as a long.
   */
  fun getLong(field: String): Long?

  fun getMetadata(): SnapshotMetadata

  /**
   * Gets the reference to the document.
   */
  fun getReference(): GoogleDocumentReference

  /**
   * Returns the value of the [field] as a String.
   */
  fun getString(field: String): String?

  /**
   * Returns the value of the [field] as a Timestamp.
   */
  fun getTimestamp(field: String, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): Timestamp?

  /**
   * Returns the value of the [field] as a Timestamp.
   */
  fun getTimestamp(field: String): Timestamp?

  /**
   * Returns the value of the [field] as a DocumentReference.
   */
  fun rxGetDocumentReference(field: String): DocumentReference?

  /**
   * Gets the reference to the document.
   */
  fun rxGetReference(): DocumentReference

  /**
   * Returns the contents of the document converted to a POJO or null if the
   * document doesn't exist.
   */
  fun <T> toObject(valueType: Class<T>): T?

  /**
   * Returns the contents of the document converted to a POJO or null if the
   * document doesn't exist.
   */
  fun <T> toObject(valueType: Class<T>, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): T?
}