package com.labs2160.rxfiredroid.firestore

import com.google.firebase.firestore.MetadataChanges
import com.google.firebase.firestore.SnapshotMetadata
import com.google.firebase.firestore.DocumentChange as GoogleDocumentChange
import com.google.firebase.firestore.DocumentSnapshot as GoogleDocumentSnapshot
import com.google.firebase.firestore.Query as GoogleQuery
import com.google.firebase.firestore.QuerySnapshot as GoogleQuerySnapshot

@Suppress("unused")
internal class QuerySnapshotImpl(private val delegate: GoogleQuerySnapshot) : QuerySnapshot {
  override fun getDocumentChanges(): List<GoogleDocumentChange> {
    return delegate.documentChanges
  }

  override fun getDocumentChanges(metadataChanges: MetadataChanges): List<GoogleDocumentChange> {
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
    val list = arrayListOf<QueryDocumentSnapshot>()

    delegate.iterator().forEach { list.add(QueryDocumentSnapshot.newInstance(it)) }

    return list.iterator()
  }

  override fun rxGetDocuments(): List<DocumentSnapshot> {
    return delegate.documents.map { DocumentSnapshot.newInstance(it) }
  }

  override fun rxGetDocumentChanges(): List<DocumentChange> {
    return delegate.documentChanges.map { DocumentChange.newInstance(it) }
  }

  override fun rxGetDocumentChanges(metadataChanges: MetadataChanges): List<DocumentChange> {
    return delegate.getDocumentChanges(metadataChanges).map { DocumentChange.newInstance(it) }
  }

  override fun rxGetQuery(): Query {
    return QueryableCollectionReference.newInstance(delegate.query)
  }

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