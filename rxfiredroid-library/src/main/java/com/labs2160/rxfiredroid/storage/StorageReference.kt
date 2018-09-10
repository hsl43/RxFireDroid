package com.labs2160.rxfiredroid.storage

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.StreamDownloadTask
import com.google.firebase.storage.UploadTask
import io.reactivex.Completable
import io.reactivex.Single
import java.io.File
import java.io.InputStream
import com.google.firebase.storage.FirebaseStorage as GoogleFirebaseStorage
import com.google.firebase.storage.StorageReference as GoogleStorageReference

interface StorageReference {
  companion object {
    fun newInstance(googleStorageReference: GoogleStorageReference): StorageReference {
      return StorageReferenceImpl(googleStorageReference)
    }
  }

  /**
   * Returns a new instance of StorageReference pointing to a child location of
   * the current reference.
   */
  fun child(pathString: String): GoogleStorageReference

  /**
   * Deletes the object at this StorageReference.
   */
  fun delete(): Task<Void>

  fun getActiveDownloadTasks(): List<FileDownloadTask>

  fun getActiveUploadTasks(): List<UploadTask>

  /**
   * Return the Google Cloud Storage bucket that holds this object.
   */
  fun getBucket(): String

  /**
   * Asynchronously downloads the object from this StorageReference A byte
   * array will be allocated large enough to hold the entire file in memory.
   */
  fun getBytes(maxDownloadSizeBytes: Long): Task<ByteArray>

  /**
   * Asynchronously retrieves a long lived download URL with a revokable token.
   */
  fun getDownloadUrl(): Task<Uri>

  /**
   * Asynchronously downloads the object at this StorageReference to a
   * specified system filepath.
   */
  fun getFile(destinationFile: File): FileDownloadTask

  /**
   * Asynchronously downloads the object at this StorageReference to a
   * specified system filepath.
   */
  fun getFile(destinationUri: Uri): FileDownloadTask

  /**
   * Retrieves metadata associated with an object at this StorageReference.
   */
  fun getMetadata(): Task<StorageMetadata>

  /**
   * Returns the short name of this object.
   */
  fun getName(): String

  /**
   * Returns a new instance of StorageReference pointing to the parent location
   * or null if this instance references the root location.
   */
  fun getParent(): GoogleStorageReference?

  /**
   * Returns the full path to this object, not including the Google Cloud
   * Storage bucket.
   */
  fun getPath(): String

  /**
   * Returns a new instance of StorageReference pointing to the root location.
   */
  fun getRoot(): GoogleStorageReference

  /**
   * Returns the FirebaseStorage service which created this reference.
   */
  fun getStorage(): GoogleFirebaseStorage

  /**
   * Asynchronously downloads the object at this StorageReference via a
   * InputStream.
   */
  fun getStream(processor: StreamDownloadTask.StreamProcessor): StreamDownloadTask

  /**
   * Asynchronously downloads the object at this StorageReference via a
   * InputStream.
   */
  fun getStream(): StreamDownloadTask

  /**
   * Asynchronously uploads byte data to this StorageReference.
   */
  fun putBytes(bytes: ByteArray, metadata: StorageMetadata): UploadTask

  /**
   * Asynchronously uploads byte data to this StorageReference.
   */
  fun putBytes(bytes: ByteArray): UploadTask

  /**
   * Asynchronously uploads from a content URI to this StorageReference.
   */
  fun putFile(uri: Uri, metadata: StorageMetadata, existingUploadUri: Uri): UploadTask

  /**
   * Asynchronously uploads from a content URI to this StorageReference.
   */
  fun putFile(uri: Uri, metadata: StorageMetadata): UploadTask

  /**
   * Asynchronously uploads from a content URI to this StorageReference.
   */
  fun putFile(uri: Uri): UploadTask

  /**
   * Asynchronously uploads a [stream] of data to this StorageReference.
   */
  fun putStream(stream: InputStream, metadata: StorageMetadata): UploadTask

  /**
   * Asynchronously uploads a [stream] of data to this StorageReference.
   */
  fun putStream(stream: InputStream): UploadTask

  /**
   * Deletes the object at this StorageReference.
   */
  fun rxDelete(): Completable

  /**
   * Asynchronously downloads the object from this StorageReference A byte
   * array will be allocated large enough to hold the entire file in memory.
   */
  fun rxGetBytes(maxDownloadSizeBytes: Long): Single<ByteArray>

  /**
   * Asynchronously retrieves a long lived download URL with a revokable token.
   */
  fun rxGetDownloadUrl(): Single<Uri>

  /**
   * Retrieves metadata associated with an object at this StorageReference.
   */
  fun rxGetMetadata(): Single<StorageMetadata>

  /**
   * Returns the FirebaseStorage service which created this reference.
   */
  fun rxGetStorage(): FirebaseStorage

  /**
   * Updates the [metadata] associated with this StorageReference.
   */
  fun rxUpdateMetadata(metadata: StorageMetadata): Single<StorageMetadata>

  /**
   * Updates the [metadata] associated with this StorageReference.
   */
  fun updateMetadata(metadata: StorageMetadata): Task<StorageMetadata>
}