package com.labs2160.rxfiredroid.firestore

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.SetOptions
import com.labs2160.rxfiredroid.addRxOnCompleteListener
import io.reactivex.Completable
import com.google.firebase.firestore.DocumentReference as GoogleDocumentReference
import com.google.firebase.firestore.WriteBatch as GoogleWriteBatch

@Suppress("unused")
internal class WriteBatchImpl(private val delegate: GoogleWriteBatch) : WriteBatch {
override fun commit(): Task<Void> {
    return delegate.commit()
  }

  override fun delete(documentRef: GoogleDocumentReference): GoogleWriteBatch {
    return delegate.delete(documentRef)
  }

  override fun rxCommit(): Completable {
    return Completable.create { emitter ->
      delegate.commit()
          .addRxOnCompleteListener(emitter, "Unexpected error in rxCommit()")
    }
  }

  override fun rxSet(documentRef: DocumentReference, data: Map<String,Any>): WriteBatch {
    return WriteBatch.newInstance(delegate.set(documentRef.delegate(), data))
  }

  override fun rxSet(documentRef: DocumentReference, data: Map<String,Any>, options: SetOptions): WriteBatch {
    return WriteBatch.newInstance(delegate.set(documentRef.delegate(), data, options))
  }

  override fun rxSet(documentRef: DocumentReference, pojo: Any): WriteBatch {
    return WriteBatch.newInstance(delegate.set(documentRef.delegate(), pojo))
  }

  override fun rxSet(documentRef: DocumentReference, pojo: Any, options: SetOptions): WriteBatch {
    return WriteBatch.newInstance(delegate.set(documentRef.delegate(), pojo, options))
  }

  override fun rxUpdate(documentRef: DocumentReference, data: Map<String,Any>): WriteBatch {
    return WriteBatch.newInstance(delegate.update(documentRef.delegate(), data))
  }

  override fun rxUpdate(
      documentRef: DocumentReference,
      field: String,
      value: Any,
      vararg moreFieldsAndValues: Any

  ): WriteBatch {

    return WriteBatch.newInstance(delegate.update(
        documentRef.delegate(),
        field,
        value,
        moreFieldsAndValues
    ))
  }

  override fun rxUpdate(
      documentRef: DocumentReference,
      fieldPath: FieldPath,
      value: Any,
      vararg moreFieldsAndValues: Any

  ): WriteBatch {

    return WriteBatch.newInstance(delegate.update(
        documentRef.delegate(),
        fieldPath,
        value,
        moreFieldsAndValues
    ))
  }

  override fun set(documentRef: GoogleDocumentReference, data: Map<String,Any>): GoogleWriteBatch {
    return delegate.set(documentRef, data)
  }

  override fun set(documentRef: GoogleDocumentReference, data: Map<String,Any>, options: SetOptions): GoogleWriteBatch {
    return delegate.set(documentRef, data, options)
  }

  override fun set(documentRef: GoogleDocumentReference, pojo: Any): GoogleWriteBatch {
    return delegate.set(documentRef, pojo)
  }

  override fun set(documentRef: GoogleDocumentReference, pojo: Any, options: SetOptions): GoogleWriteBatch {
    return delegate.set(documentRef, pojo, options)
  }

  override fun update(documentRef: GoogleDocumentReference, data: Map<String,Any>): GoogleWriteBatch {
    return delegate.update(documentRef, data)
  }

  override fun update(
      documentRef: GoogleDocumentReference,
      field: String,
      value: Any,
      vararg moreFieldsAndValues: Any

  ): GoogleWriteBatch {

    return delegate.update(documentRef, field, value, moreFieldsAndValues)
  }

  override fun update(
      documentRef: GoogleDocumentReference,
      fieldPath: FieldPath,
      value: Any,
      vararg moreFieldsAndValues: Any

  ): GoogleWriteBatch {

    return delegate.update(documentRef, fieldPath, value, moreFieldsAndValues)
  }
}