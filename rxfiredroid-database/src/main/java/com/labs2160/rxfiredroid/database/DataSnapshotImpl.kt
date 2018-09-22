package com.labs2160.rxfiredroid.database

import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.DataSnapshot as GoogleDataSnapshot
import com.google.firebase.database.DatabaseReference as GoogleDatabaseReference

@Suppress("unused")
internal class DataSnapshotImpl(private val delegate: GoogleDataSnapshot) : DataSnapshot {
  override fun child(path: String): GoogleDataSnapshot {
    return delegate.child(path)
  }

  override fun exists(): Boolean {
    return delegate.exists()
  }

  override fun getChildren(): Iterable<GoogleDataSnapshot> {
    return delegate.children
  }

  override fun getChildrenCount(): Long {
    return delegate.childrenCount
  }

  override fun getKey(): String? {
    return delegate.key
  }

  override fun getPriority(): Any? {
    return delegate.priority
  }

  override fun getRef(): GoogleDatabaseReference {
    return delegate.ref
  }

  override fun getValue(useExportFormat: Boolean): Any? {
    return delegate.getValue(useExportFormat)
  }

  override fun <T> getValue(t: GenericTypeIndicator<T>): T? {
    return delegate.getValue(t)
  }

  override fun getValue(): Any? {
    return delegate.value
  }

  override fun <T> getValue(valueType: Class<T>): T? {
    return delegate.getValue(valueType)
  }

  override fun hasChild(path: String): Boolean {
    return delegate.hasChild(path)
  }

  override fun hasChildren(): Boolean {
    return delegate.hasChildren()
  }

  override fun rxChild(path: String): DataSnapshot {
    return DataSnapshotImpl(delegate.child(path))
  }

  override fun rxGetChildren(): Iterable<DataSnapshot> {
    return delegate.children.map { DataSnapshotImpl(it) }
  }

  override fun rxGetRef(): DatabaseReference {
    return DatabaseReference.newInstance(delegate.ref)
  }
}