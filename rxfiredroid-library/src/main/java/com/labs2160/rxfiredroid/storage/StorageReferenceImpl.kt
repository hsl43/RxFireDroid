package com.labs2160.rxfiredroid.storage

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.StreamDownloadTask
import com.google.firebase.storage.UploadTask
import com.labs2160.rxfiredroid.addRxOnCompleteListener
import io.reactivex.Completable
import io.reactivex.Single
import java.io.File
import java.io.InputStream
import com.google.firebase.storage.FirebaseStorage as GoogleFirebaseStorage
import com.google.firebase.storage.StorageReference as GoogleStorageReference

@Suppress("unused")
internal class StorageReferenceImpl(private val delegate: GoogleStorageReference) : StorageReference {
  override fun child(pathString: String): GoogleStorageReference {
    return child(pathString)
  }

  override fun delete(): Task<Void> {
    return delegate.delete()
  }

  override fun getActiveDownloadTasks(): List<FileDownloadTask> {
    return delegate.activeDownloadTasks
  }

  override fun getActiveUploadTasks(): List<UploadTask> {
    return delegate.activeUploadTasks
  }

  override fun getBucket(): String {
    return delegate.bucket
  }

  override fun getBytes(maxDownloadSizeBytes: Long): Task<ByteArray> {
    return delegate.getBytes(maxDownloadSizeBytes)
  }

  override fun getDownloadUrl(): Task<Uri> {
    return delegate.downloadUrl
  }

  override fun getFile(destinationFile: File): FileDownloadTask {
    return delegate.getFile(destinationFile)
  }

  override fun getFile(destinationUri: Uri): FileDownloadTask {
    return delegate.getFile(destinationUri)
  }

  override fun getMetadata(): Task<StorageMetadata> {
    return delegate.metadata
  }

  override fun getName(): String {
    return delegate.name
  }

  override fun getParent(): GoogleStorageReference? {
    return delegate.parent
  }

  override fun getPath(): String {
    return delegate.path
  }

  override fun getRoot(): GoogleStorageReference {
    return delegate.root
  }

  override fun getStorage(): GoogleFirebaseStorage {
    return delegate.storage
  }

  override fun getStream(processor: StreamDownloadTask.StreamProcessor): StreamDownloadTask {
    return delegate.getStream(processor)
  }

  override fun getStream(): StreamDownloadTask {
    return delegate.stream
  }

  override fun putBytes(bytes: ByteArray, metadata: StorageMetadata): UploadTask {
    return delegate.putBytes(bytes, metadata)
  }

  override fun putBytes(bytes: ByteArray): UploadTask {
    return delegate.putBytes(bytes)
  }

  override fun putFile(uri: Uri, metadata: StorageMetadata, existingUploadUri: Uri): UploadTask {
    return delegate.putFile(uri, metadata, existingUploadUri)
  }

  override fun putFile(uri: Uri, metadata: StorageMetadata): UploadTask {
    return delegate.putFile(uri, metadata)
  }

  override fun putFile(uri: Uri): UploadTask {
    return delegate.putFile(uri)
  }

  override fun putStream(stream: InputStream, metadata: StorageMetadata): UploadTask {
    return delegate.putStream(stream, metadata)
  }

  override fun putStream(stream: InputStream): UploadTask {
    return delegate.putStream(stream)
  }

  override fun rxDelete(): Completable {
    return Completable.create { emitter ->
      delegate.delete()
          .addRxOnCompleteListener(emitter, "Unexpected error in rxDelete()")
    }
  }

  override fun rxGetBytes(maxDownloadSizeBytes: Long): Single<ByteArray> {
    return Single.create { emitter ->
      delegate.getBytes(maxDownloadSizeBytes)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxGetBytes()")
    }
  }

  override fun rxGetDownloadUrl(): Single<Uri> {
    return Single.create { emitter ->
      delegate.downloadUrl
          .addRxOnCompleteListener(emitter, "Unexpected error in rxGetDownloadUrl()")
    }
  }

  override fun rxGetMetadata(): Single<StorageMetadata> {
    return Single.create { emitter ->
      delegate.metadata
          .addRxOnCompleteListener(emitter, "Unexpected error in rxGetMetadata()")
    }
  }

  override fun rxGetStorage(): FirebaseStorage {
    return FirebaseStorage.getInstance()
  }

  override fun rxUpdateMetadata(metadata: StorageMetadata): Single<StorageMetadata> {
    return Single.create { emitter ->
      delegate.updateMetadata(metadata)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxUpdateMetadata()")
    }
  }

  override fun updateMetadata(metadata: StorageMetadata): Task<StorageMetadata> {
    return delegate.updateMetadata(metadata)
  }
}