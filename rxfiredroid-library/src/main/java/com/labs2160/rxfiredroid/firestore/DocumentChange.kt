package com.labs2160.rxfiredroid.firestore

import com.google.firebase.firestore.DocumentChange as GoogleDocumentChange
import com.google.firebase.firestore.QueryDocumentSnapshot as GoogleQueryDocumentSnapshot

interface DocumentChange {
  companion object {
    fun newInstance(googleDocumentChange: GoogleDocumentChange): DocumentChange {
      return DocumentChangeImpl(googleDocumentChange)
    }
  }

  /**
   * Returns the newly added or modified document if this DocumentChange is for
   * an updated document.
   */
  fun getDocument(): GoogleQueryDocumentSnapshot

  /**
   * The index of the changed document in the result set immediately after this
   * DocumentChange (i.e.
   */
  fun getNewIndex(): Int

  /**
   * The index of the changed document in the result set immediately prior to
   * this DocumentChange (i.e.
   */
  fun getOldIndex(): Int

  fun getType(): GoogleDocumentChange.Type

  /**
   * Returns the newly added or modified document if this DocumentChange is for
   * an updated document.
   */
  fun rxGetDocument(): QueryDocumentSnapshot
}