package com.labs2160.rxfiredroid.firestore

import com.google.android.gms.tasks.Task
import io.reactivex.Single
import com.google.firebase.firestore.DocumentReference as GoogleDocumentReference

interface CollectionReference : Query {
  /**
   * Adds a new document to this collection with the specified [data],
   * assigning it a document ID automatically.
   */
  fun add(data: Map<String,Any>): Task<GoogleDocumentReference>

  /**
   * Adds a new document to this collection with the specified [POJO] as
   * contents, assigning it a document ID automatically.
   */
  fun add(pojo: Any): Task<GoogleDocumentReference>

  /**
   * Returns a DocumentReference pointing to a new document with an
   * auto-generated ID within this collection.
   */
  fun document(): GoogleDocumentReference

  /**
   * Gets a DocumentReference instance that refers to the document at the
   * specified [path] within this collection.
   */
  fun document(path: String): GoogleDocumentReference

  fun getId(): String

  /**
   * Gets a DocumentReference to the document that contains this collection.
   */
  fun getParent(): GoogleDocumentReference?

  /**
   * Gets the path of this collection (relative to the root of the database) as
   * a slash-separated string.
   */
  fun getPath(): String

  /**
   * Adds a new document to this collection with the specified [data],
   * assigning it a document ID automatically.
   */
  fun rxAdd(data: Map<String,Any>): Single<DocumentReference>

  /**
   * Adds a new document to this collection with the specified [POJO] as
   * contents, assigning it a document ID automatically.
   */
  fun rxAdd(pojo: Any): Single<DocumentReference>

  /**
   * Returns a DocumentReference pointing to a new document with an
   * auto-generated ID within this collection.
   */
  fun rxDocument(): DocumentReference

  /**
   * Gets a DocumentReference instance that refers to the document at the
   * specified [path] within this collection.
   */
  fun rxDocument(path: String): DocumentReference

  /**
   * Gets a DocumentReference to the document that contains this collection.
   */
  fun rxGetParent(): DocumentReference?
}