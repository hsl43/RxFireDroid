package com.labs2160.rxfiredroid.firestore

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.SnapshotMetadata
import java.util.*
import com.google.firebase.firestore.DocumentReference as GoogleDocumentReference
import com.google.firebase.firestore.DocumentSnapshot as GoogleDocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot as GoogleQueryDocumentSnapshot

internal class QueryDocumentSnapshotImpl(
    private var documentDelegate: GoogleDocumentSnapshot? = null,
    private var queryDocumentDelegate: GoogleQueryDocumentSnapshot? = null

) : QueryDocumentSnapshot {

  override fun getData(serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): Map<String,Any> {
    return (queryDocumentDelegate ?: documentDelegate)?.getData(serverTimestampBehavior)
        ?: throw IllegalStateException("getData() was invoked without a backing delegate")
  }

  override fun getData(): Map<String,Any> {
    return (queryDocumentDelegate ?: documentDelegate)?.data
        ?: throw IllegalStateException("getData() was invoked without a backing delegate")
  }

  override fun <T> toObject(valueType: Class<T>): T {
    return (queryDocumentDelegate ?: documentDelegate)?.toObject(valueType)
        ?: throw IllegalStateException("toObject() was invoked without a backing delegate")
  }

  override fun <T> toObject(valueType: Class<T>, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): T {
    return (queryDocumentDelegate ?: documentDelegate)?.toObject(valueType, serverTimestampBehavior)
        ?: throw IllegalStateException("toObject() was invoked without a backing delegate")
  }

  override fun contains(fieldPath: FieldPath): Boolean {
    return documentDelegate?.contains(fieldPath)
        ?: throw IllegalStateException("contains() was invoked without a backing DocumentSnapshot")
  }

  override fun contains(field: String): Boolean {
    return documentDelegate?.contains(field)
        ?: throw IllegalStateException("contains() was invoked without a backing DocumentSnapshot")
  }

  override fun exists(): Boolean {
    return documentDelegate?.exists()
        ?: throw IllegalStateException("exists() was invoked without a backing DocumentSnapshot")
  }

  override fun get(field: String, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): Any? {
    return documentDelegate?.get(field, serverTimestampBehavior)
  }

  override fun get(fieldPath: FieldPath): Any? {
    return documentDelegate?.get(fieldPath)
  }

  override fun get(fieldPath: FieldPath, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): Any? {
    return documentDelegate?.get(fieldPath, serverTimestampBehavior)
  }

  override fun get(field: String): Any? {
    return documentDelegate?.get(field)
  }

  override fun getBlob(field: String): Blob? {
    return documentDelegate?.getBlob(field)
  }

  override fun getBoolean(field: String): Boolean? {
    return documentDelegate?.getBoolean(field)
  }

  override fun getDate(field: String): Date? {
    return documentDelegate?.getDate(field)
  }

  override fun getDate(field: String, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): Date? {
    return documentDelegate?.getDate(field, serverTimestampBehavior)
  }

  override fun getDocumentReference(field: String): GoogleDocumentReference? {
    return documentDelegate?.getDocumentReference(field)
  }

  override fun getDouble(field: String): Double? {
    return documentDelegate?.getDouble(field)
  }

  override fun getGeoPoint(field: String): GeoPoint? {
    return documentDelegate?.getGeoPoint(field)
  }

  override fun getId(): String {
    return documentDelegate?.id
        ?: throw IllegalStateException("getId() was invoked without a backing DocumentSnapshot")
  }

  override fun getLong(field: String): Long? {
    return documentDelegate?.getLong(field)
  }

  override fun getMetadata(): SnapshotMetadata {
    return documentDelegate?.metadata
        ?: throw IllegalStateException("getMetadata() was invoked without a backing DocumentSnapshot")
  }

  override fun getReference(): GoogleDocumentReference {
    return documentDelegate?.reference
        ?: throw IllegalStateException("getReference() was invoked without a backing DocumentSnapshot")
  }

  override fun getString(field: String): String? {
    return documentDelegate?.getString(field)
  }

  override fun getTimestamp(field: String, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): Timestamp? {
    return documentDelegate?.getTimestamp(field, serverTimestampBehavior)
  }

  override fun getTimestamp(field: String): Timestamp? {
    return documentDelegate?.getTimestamp(field)
  }

  override fun rxGetDocumentReference(field: String): DocumentReference? {
    val documentReference = documentDelegate?.getDocumentReference(field)

    return if(documentReference == null) {
      null
    } else {
      return DocumentReference.newInstance(documentReference)
    }
  }

  override fun rxGetReference(): DocumentReference {
    return DocumentReference.newInstance(getReference())
  }
}