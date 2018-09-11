package com.labs2160.rxfiredroid.firestore

import com.google.firebase.firestore.DocumentChange as GoogleDocumentChange
import com.google.firebase.firestore.QueryDocumentSnapshot as GoogleQueryDocumentSnapshot

internal class DocumentChangeImpl(private val delegate: GoogleDocumentChange) : DocumentChange {
  override fun getDocument(): GoogleQueryDocumentSnapshot {
    return delegate.document
  }

  override fun getNewIndex(): Int {
    return delegate.newIndex
  }

  override fun getOldIndex(): Int {
    return delegate.oldIndex
  }

  override fun getType(): GoogleDocumentChange.Type {
    return delegate.type
  }

  override fun rxGetDocument(): QueryDocumentSnapshot {
    return QueryDocumentSnapshot.newInstance(delegate.document)
  }
}