package com.labs2160.rxfiredroid.firestore

import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.MetadataChanges
import com.google.firebase.firestore.SnapshotMetadata
import com.google.firebase.firestore.DocumentSnapshot as GoogleDocumentSnapshot
import com.google.firebase.firestore.Query as GoogleQuery
import com.google.firebase.firestore.QuerySnapshot as GoogleQuerySnapshot

@Suppress("unused")
internal class QuerySnapshotImpl(private val delegate: GoogleQuerySnapshot) : QuerySnapshot {
  override fun getDocumentChanges(): List<DocumentChange> {
    return delegate.documentChanges
  }

  override fun getDocumentChanges(metadataChanges: MetadataChanges): List<DocumentChange> {
    return delegate.getDocumentChanges(metadataChanges)
  }

  override fun getDocuments(): List<GoogleDocumentSnapshot> {
    return delegate.documents
  }

  override fun getMetadata(): SnapshotMetadata {
    return delegate.metadata
  }

  override fun getQuery(): GoogleQuery {
    return delegate.query
  }

  override fun isEmpty(): Boolean {
    return delegate.isEmpty
  }

  override fun iterator(): Iterator<QueryDocumentSnapshot> {
    delegate.iterator().forEach {  }
    TODO()
  }

  override fun rxGetDocuments(): List<DocumentSnapshot> {
    return delegate.documents.map { DocumentSnapshot.newInstance(it) }
  }

//  override fun rxGetQuery(): Query {
//    TODO()
//  }

  override fun size(): Int {
    return delegate.size()
  }

  override fun <T> toObjects(clazz: Class<T>, serverTimestampBehavior: GoogleDocumentSnapshot.ServerTimestampBehavior): List<T> {
    return delegate.toObjects(clazz, serverTimestampBehavior)
  }

  override fun <T> toObjects(clazz: Class<T>): List<T> {
    return delegate.toObjects(clazz)
  }
}