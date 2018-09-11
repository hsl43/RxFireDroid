package com.labs2160.rxfiredroid.firestore

import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.DocumentReference as GoogleDocumentReference
import com.google.firebase.firestore.DocumentSnapshot as GoogleDocumentSnapshot
import com.google.firebase.firestore.Transaction as GoogleTransaction

interface Transaction {
  companion object {
    fun newInstance(googleTransaction: GoogleTransaction): Transaction {
      return newInstance(googleTransaction)
    }
  }

  /**
   * Deletes the document referred to by the provided DocumentReference.
   */
  fun delete(documentRef: GoogleDocumentReference): GoogleTransaction

  /**
   * For internal use in RxFireDroid only.
   */
  fun delegate(): GoogleTransaction

  /**
   * Reads the document referenced by this DocumentReference
   */
  fun get(documentRef: GoogleDocumentReference): GoogleDocumentSnapshot

  /**
   * Deletes the document referred to by the provided DocumentReference.
   */
  fun rxDelete(documentRef: DocumentReference): Transaction

  /**
   * Reads the document referenced by this DocumentReference
   */
  fun rxGet(documentRef: DocumentReference): DocumentSnapshot

  /**
   * Overwrites the document referred to by the provided DocumentReference.
   */
  fun rxSet(documentRef: DocumentReference, data: Map<String,Any>): Transaction

  /**
   * Writes to the document referred to by the provided DocumentReference.
   */
  fun rxSet(documentRef: DocumentReference, data: Map<String,Any>, options: SetOptions): Transaction

  /**
   * Overwrites the document referred to by the provided DocumentReference.
   */
  fun rxSet(documentRef: DocumentReference, pojo: Any): Transaction

  /**
   * Writes to the document referred to by the provided DocumentReference.
   */
  fun rxSet(documentRef: DocumentReference, pojo: Any, options: SetOptions): Transaction

  /**
   * Updates fields in the document referred to by the provided
   * DocumentReference.
   */
  fun rxUpdate(documentRef: DocumentReference, data: Map<String,Any>): Transaction

  /**
   * Updates fields in the document referred to by the provided
   * DocumentReference.
   */
  fun rxUpdate(
      documentRef: DocumentReference,
      field: String,
      value: Any,
      vararg moreFieldsAndValues: Any

  ): Transaction

  /**
   * Updates fields in the document referred to by the provided
   * DocumentReference.
   */
  fun rxUpdate(
      documentRef: DocumentReference,
      fieldPath: FieldPath,
      value: Any,
      vararg moreFieldsAndValues: Any

  ): Transaction

  /**
   * Overwrites the document referred to by the provided DocumentReference.
   */
  fun set(documentRef: GoogleDocumentReference, data: Map<String,Any>): GoogleTransaction

  /**
   * Writes to the document referred to by the provided DocumentReference.
   */
  fun set(documentRef: GoogleDocumentReference, data: Map<String,Any>, options: SetOptions): GoogleTransaction

  /**
   * Overwrites the document referred to by the provided DocumentReference.
   */
  fun set(documentRef: GoogleDocumentReference, pojo: Any): GoogleTransaction

  /**
   * Writes to the document referred to by the provided DocumentReference.
   */
  fun set(documentRef: GoogleDocumentReference, pojo: Any, options: SetOptions): GoogleTransaction

  /**
   * Updates fields in the document referred to by the provided
   * DocumentReference.
   */
  fun update(documentRef: GoogleDocumentReference, data: Map<String,Any>): GoogleTransaction

  /**
   * Updates fields in the document referred to by the provided
   * DocumentReference.
   */
  fun update(
      documentRef: GoogleDocumentReference,
      field: String,
      value: Any,
      vararg moreFieldsAndValues: Any

  ): GoogleTransaction

  /**
   * Updates fields in the document referred to by the provided
   * DocumentReference.
   */
  fun update(
      documentRef: GoogleDocumentReference,
      fieldPath: FieldPath,
      value: Any,
      vararg moreFieldsAndValues: Any

  ): GoogleTransaction
}