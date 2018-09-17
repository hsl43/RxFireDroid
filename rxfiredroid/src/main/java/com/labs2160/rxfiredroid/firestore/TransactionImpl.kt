package com.labs2160.rxfiredroid.firestore

import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.DocumentReference as GoogleDocumentReference
import com.google.firebase.firestore.DocumentSnapshot as GoogleDocumentSnapshot
import com.google.firebase.firestore.Transaction as GoogleTransaction

@Suppress("unused")
internal class TransactionImpl(private val delegate: GoogleTransaction) : Transaction {
  override fun delete(documentRef: GoogleDocumentReference): GoogleTransaction {
    return delegate.delete(documentRef)
  }

  override fun delegate(): GoogleTransaction {
    return delegate
  }

  override fun get(documentRef: GoogleDocumentReference): GoogleDocumentSnapshot {
    return delegate.get(documentRef)
  }

  override fun rxDelete(documentRef: DocumentReference): Transaction {
    return TransactionImpl(delegate.delete(documentRef.delegate()))
  }

  override fun rxGet(documentRef: DocumentReference): DocumentSnapshot {
    return DocumentSnapshot.newInstance(delegate.get(documentRef.delegate()))
  }

  override fun rxSet(documentRef: DocumentReference, data: Map<String,Any>): Transaction {
    return TransactionImpl(delegate.set(documentRef.delegate(), data))
  }

  override fun rxSet(documentRef: DocumentReference, data: Map<String,Any>, options: SetOptions): Transaction {
    return TransactionImpl(delegate.set(documentRef.delegate(), data, options))
  }

  override fun rxSet(documentRef: DocumentReference, pojo: Any): Transaction {
    return TransactionImpl(delegate.set(documentRef.delegate(), pojo))
  }

  override fun rxSet(documentRef: DocumentReference, pojo: Any, options: SetOptions): Transaction {
    return TransactionImpl(delegate.set(documentRef.delegate(), pojo, options))
  }

  override fun rxUpdate(documentRef: DocumentReference, data: Map<String,Any>): Transaction {
    return TransactionImpl(delegate.update(documentRef.delegate(), data))
  }

  override fun rxUpdate(
      documentRef: DocumentReference,
      field: String,
      value: Any, vararg
      moreFieldsAndValues: Any

  ): Transaction {

    return TransactionImpl(delegate.update(documentRef.delegate(), field, value, moreFieldsAndValues))
  }

  override fun rxUpdate(
      documentRef: DocumentReference,
      fieldPath: FieldPath,
      value: Any,
      vararg moreFieldsAndValues: Any

  ): Transaction {

    return TransactionImpl(delegate.update(documentRef.delegate(), fieldPath, value, moreFieldsAndValues))
  }

  override fun set(documentRef: GoogleDocumentReference, data: Map<String,Any>): GoogleTransaction {
    return delegate.set(documentRef, data)
  }

  override fun set(documentRef: GoogleDocumentReference, data: Map<String,Any>, options: SetOptions): GoogleTransaction {
    return delegate.set(documentRef, data, options)
  }

  override fun set(documentRef: GoogleDocumentReference, pojo: Any): GoogleTransaction {
    return delegate.set(documentRef, pojo)
  }

  override fun set(documentRef: GoogleDocumentReference, pojo: Any, options: SetOptions): GoogleTransaction {
    return delegate.set(documentRef, pojo, options)
  }

  override fun update(documentRef: GoogleDocumentReference, data: Map<String,Any>): GoogleTransaction {
    return delegate.update(documentRef, data)
  }

  override fun update(
      documentRef: GoogleDocumentReference,
      field: String,
      value: Any,
      vararg moreFieldsAndValues: Any

  ): GoogleTransaction {

    return delegate.update(documentRef, field, value, moreFieldsAndValues)
  }

  override fun update(
      documentRef: GoogleDocumentReference,
      fieldPath: FieldPath,
      value: Any,
      vararg moreFieldsAndValues: Any

  ): GoogleTransaction {

    return delegate.update(documentRef, fieldPath, value, moreFieldsAndValues)
  }
}