package com.labs2160.rxfiredroid.firestore

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.SnapshotMetadata
import java.util.*
import com.google.firebase.firestore.DocumentReference as GoogleDocumentReference
import com.google.firebase.firestore.DocumentSnapshot as GoogleDocumentSnapshot

@Suppress("unused")
internal class DocumentSnapshotImpl(private val delegate: GoogleDocumentSnapshot) : DocumentSnapshot {
  override fun contains(fieldPath: FieldPath): Boolean {
    return delegate.contains(fieldPath)
  }

  override fun contains(field: String): Boolean {
    return delegate.contains(field)
  }

  override fun exists(): Boolean {
    return delegate.exists()
  }

  override fun get(field: String, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): Any? {
    return delegate.get(field, serverTimestampBehavior)
  }

  override fun get(fieldPath: FieldPath): Any? {
    return delegate.get(fieldPath)
  }

  override fun get(fieldPath: FieldPath, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): Any? {
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

  override fun getData(serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): Map<String,Any>? {
    return delegate.getData(serverTimestampBehavior)
  }

  override fun getData(): Map<String,Any>? {
    return delegate.data
  }

  override fun getDate(field: String): Date? {
    return delegate.getDate(field)
  }

  override fun getDate(field: String, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): Date? {
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

  override fun getTimestamp(field: String, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): Timestamp? {
    return delegate.getTimestamp(field, serverTimestampBehavior)
  }

  override fun getTimestamp(field: String): Timestamp? {
    return delegate.getTimestamp(field)
  }

  override fun rxGetDocumentReference(field: String): DocumentReference? {
    val ref = delegate.getDocumentReference(field)

    return if(ref == null) {
      null
    } else {
      DocumentReference.newInstance(ref)
    }
  }

  override fun rxGetReference(): DocumentReference {
    return DocumentReference.newInstance(delegate.reference)
  }

  override fun <T> toObject(valueType: Class<T>): T? {
    return delegate.toObject(valueType)
  }

  override fun <T> toObject(valueType: Class<T>, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): T? {
    return delegate.toObject(valueType, serverTimestampBehavior)
  }
}