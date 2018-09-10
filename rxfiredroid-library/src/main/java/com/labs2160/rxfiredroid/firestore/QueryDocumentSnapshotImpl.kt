package com.labs2160.rxfiredroid.firestore

import com.google.firebase.Timestamp
import com.google.firebase.firestore.*
import com.google.firebase.firestore.DocumentSnapshot
import java.util.*
import com.google.firebase.firestore.DocumentReference as GoogleDocumentReference
import com.google.firebase.firestore.QueryDocumentSnapshot as GoogleQueryDocumentSnapshot

internal class QueryDocumentSnapshotImpl(private val delegate: GoogleQueryDocumentSnapshot) : QueryDocumentSnapshot {
  override fun getData(serverTimestampBehavior: DocumentSnapshot.ServerTimestampBehavior): Map<String,Any> {
    return delegate.getData(serverTimestampBehavior)
  }

  override fun getData(): Map<String,Any> {
    return delegate.data
  }

  override fun <T> toObject(valueType: Class<T>): T {
    return delegate.toObject(valueType)
  }

  override fun <T> toObject(valueType: Class<T>, serverTimestampBehavior: DocumentSnapshot.ServerTimestampBehavior): T {
    return delegate.toObject(valueType, serverTimestampBehavior)
  }

  override fun contains(fieldPath: FieldPath): Boolean {
    return delegate.contains(fieldPath)
  }

  override fun contains(field: String): Boolean {
    return delegate.contains(field)
  }

  override fun exists(): Boolean {
    return delegate.exists()
  }

  override fun get(field: String, serverTimestampBehavior: DocumentSnapshot.ServerTimestampBehavior): Any? {
    return delegate.get(field, serverTimestampBehavior)
  }

  override fun get(fieldPath: FieldPath): Any? {
    return delegate.get(fieldPath)
  }

  override fun get(fieldPath: FieldPath, serverTimestampBehavior: DocumentSnapshot.ServerTimestampBehavior): Any? {
    return delegate.get(fieldPath, serverTimestampBehavior)
  }

  override fun get(field: String): Any? {
    return delegate.get(field)
  }

  override fun getBlob(field: String): Blob? {
    return delegate.getBlob(field)
  }

  override fun getBoolean(field: String): Boolean? {
    return delegate.getBoolean(field)
  }

  override fun getDate(field: String): Date? {
    return delegate.getDate(field)
  }

  override fun getDate(field: String, serverTimestampBehavior: DocumentSnapshot.ServerTimestampBehavior): Date? {
    return delegate.getDate(field, serverTimestampBehavior)
  }

  override fun getDocumentReference(field: String): GoogleDocumentReference? {
    return delegate.getDocumentReference(field)
  }

  override fun getDouble(field: String): Double? {
    return delegate.getDouble(field)
  }

  override fun getGeoPoint(field: String): GeoPoint? {
    return delegate.getGeoPoint(field)
  }

  override fun getId(): String {
    return delegate.id
  }

  override fun getLong(field: String): Long? {
    return delegate.getLong(field)
  }

  override fun getMetadata(): SnapshotMetadata {
    return delegate.metadata
  }

  override fun getReference(): GoogleDocumentReference {
    return delegate.reference
  }

  override fun getString(field: String): String? {
    return delegate.getString(field)
  }

  override fun getTimestamp(field: String, serverTimestampBehavior: DocumentSnapshot.ServerTimestampBehavior): Timestamp? {
    return delegate.getTimestamp(field, serverTimestampBehavior)
  }

  override fun getTimestamp(field: String): Timestamp? {
    return delegate.getTimestamp(field)
  }

  override fun rxGetDocumentReference(field: String): DocumentReference? {
    val documentReference = delegate.getDocumentReference(field)

    return if(documentReference == null) {
      null
    } else {
      return DocumentReference.newInstance(documentReference)
    }
  }

  override fun rxGetReference(): DocumentReference {
    return DocumentReference.newInstance(delegate.reference)
  }
}