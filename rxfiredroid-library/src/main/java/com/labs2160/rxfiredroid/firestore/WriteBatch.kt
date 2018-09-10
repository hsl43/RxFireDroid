package com.labs2160.rxfiredroid.firestore

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.SetOptions
import io.reactivex.Completable
import com.google.firebase.firestore.DocumentReference as GoogleDocumentReference
import com.google.firebase.firestore.WriteBatch as GoogleWriteBatch

interface WriteBatch {
  companion object {
    fun newInstance(googleWriteBatch: GoogleWriteBatch): WriteBatch {
      return WriteBatchImpl(googleWriteBatch)
    }
  }

  /**
   * Commits all of the writes in this write batch as a single atomic unit.
   */
  fun commit(): Task<Void>

  /**
   * Deletes the document referred to by the provided DocumentReference.
   */
  fun delete(documentRef: GoogleDocumentReference): GoogleWriteBatch

  /**
   * Commits all of the writes in this write batch as a single atomic unit.
   */
  fun rxCommit(): Completable

  /**
   * Overwrites the document referred to by the provided DocumentReference.
   */
  fun set(documentRef: GoogleDocumentReference, data: Map<String,Any>): GoogleWriteBatch

  /**
   * Writes to the document referred to by the provided DocumentReference.
   */
  fun set(documentRef: GoogleDocumentReference, data: Map<String,Any>, options: SetOptions): GoogleWriteBatch

  /**
   * Overwrites the document referred to by the provided DocumentReference.
   */
  fun set(documentRef: GoogleDocumentReference, pojo: Any): GoogleWriteBatch

  /**
   * Writes to the document referred to by the provided DocumentReference.
   */
  fun set(documentRef: GoogleDocumentReference, pojo: Any, options: SetOptions): GoogleWriteBatch

  /**
   * Updates fields in the document referred to by the provided DocumentReference.
   */
  fun update(documentRef: GoogleDocumentReference, data: Map<String,Any>): GoogleWriteBatch

  /**
   * Updates field in the document referred to by the provided DocumentReference.
   */
  fun update(
      documentRef: GoogleDocumentReference,
      field: String,
      value: Any,
      vararg moreFieldsAndValues: Any

  ): GoogleWriteBatch

  /**
   * Updates fields in the document referred to by the provided DocumentReference.
   */
  fun update(
      documentRef: GoogleDocumentReference,
      fieldPath: FieldPath,
      value: Any,
      vararg moreFieldsAndValues: Any

  ): GoogleWriteBatch
}