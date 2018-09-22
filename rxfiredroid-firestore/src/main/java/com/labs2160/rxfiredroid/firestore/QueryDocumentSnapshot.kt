package com.labs2160.rxfiredroid.firestore

import com.google.firebase.firestore.DocumentSnapshot as GoogleDocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot as GoogleQueryDocumentSnapshot

interface QueryDocumentSnapshot : DocumentSnapshot {
  companion object {
    fun newInstance(googleDocumentSnapshot: GoogleDocumentSnapshot): QueryDocumentSnapshot {
      return QueryDocumentSnapshotImpl(googleDocumentSnapshot)
    }

    fun newInstance(googleQueryDocumentSnapshot: GoogleQueryDocumentSnapshot): QueryDocumentSnapshot {
      return QueryDocumentSnapshotImpl(googleQueryDocumentSnapshot)
    }
  }

  /**
   * Returns the fields of the document as a Map.
   */
  override fun getData(serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): Map<String,Any>

  /**
   * Returns the fields of the document as a Map.
   */
  override fun getData(): Map<String,Any>

  /**
   * Returns the contents of the document converted to a POJO.
   */
  override fun <T> toObject(valueType: Class<T>): T

  /**
   * Returns the contents of the document converted to a POJO.
   */
  override fun <T> toObject(valueType: Class<T>, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): T
}