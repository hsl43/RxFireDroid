package com.labs2160.rxfiredroid.firestore

import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.MetadataChanges
import com.google.firebase.firestore.SnapshotMetadata
import com.google.firebase.firestore.DocumentSnapshot as GoogleDocumentSnapshot
import com.google.firebase.firestore.Query as GoogleQuery
import com.google.firebase.firestore.QuerySnapshot as GoogleQuerySnapshot

interface QuerySnapshot : Iterable<QueryDocumentSnapshot>{
  companion object {
    fun newInstance(googleQuerySnapshot: GoogleQuerySnapshot): QuerySnapshot {
      return QuerySnapshotImpl(googleQuerySnapshot)
    }
  }

  /**
   * Returns the list of documents that changed since the last snapshot.
   */
  fun getDocumentChanges(): List<DocumentChange>

  /**
   * Returns the list of documents that changed since the last snapshot.
   */
  fun getDocumentChanges(metadataChanges: MetadataChanges): List<DocumentChange>

  /**
   * Returns the documents in this QuerySnapshot as a List in order of the
   * query.
   */
  fun getDocuments(): List<GoogleDocumentSnapshot>

  fun getMetadata(): SnapshotMetadata

  fun getQuery(): GoogleQuery

  /**
   * Returns true if there are no documents in the QuerySnapshot.
   */
  fun isEmpty(): Boolean

  override fun iterator(): Iterator<QueryDocumentSnapshot>

  /**
   * Returns the documents in this QuerySnapshot as a List in order of the
   * query.
   */
  fun rxGetDocuments(): List<DocumentSnapshot>

//  fun rxGetQuery(): Query  // TODO

  /**
   * Returns the number of documents in the QuerySnapshot.
   */
  fun size(): Int

  /**
   * Returns the contents of the documents in the QuerySnapshot, converted to
   * the provided class, as a list.
   */
  fun <T> toObjects(clazz: Class<T>, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): List<T>

  /**
   * Returns the contents of the documents in the QuerySnapshot, converted to
   * the provided class, as a list.
   */
  fun <T> toObjects(clazz: Class<T>): List<T>
}