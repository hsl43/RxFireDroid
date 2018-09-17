package com.labs2160.rxfiredroid.config

import com.google.android.gms.tasks.Task
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
import com.labs2160.rxfiredroid.addRxOnCompleteListener
import io.reactivex.Completable
import com.google.firebase.remoteconfig.FirebaseRemoteConfig as GoogleFirebaseRemoteConfig

@Suppress("unused")
internal class FirebaseRemoteConfigImpl(private val delegate: GoogleFirebaseRemoteConfig) : FirebaseRemoteConfig {
  override fun activateFetched(): Boolean {
    return delegate.activateFetched()
  }

  override fun fetch(cacheExpirationSeconds: Long): Task<Void> {
    return delegate.fetch(cacheExpirationSeconds)
  }

  override fun fetch(): Task<Void> {
    return delegate.fetch()
  }

  override fun getBoolean(key: String, namespace: String): Boolean {
    return delegate.getBoolean(key, namespace)
  }

  override fun getBoolean(key: String): Boolean {
    return delegate.getBoolean(key)
  }

  override fun getByteArray(key: String, namespace: String): ByteArray {
    return delegate.getByteArray(key, namespace)
  }

  override fun getByteArray(key: String): ByteArray {
    return delegate.getByteArray(key)
  }

  override fun getDouble(key: String, namespace: String): Double {
    return delegate.getDouble(key, namespace)
  }

  override fun getDouble(key: String): Double {
    return delegate.getDouble(key)
  }

  override fun getInfo(): FirebaseRemoteConfigInfo {
    return delegate.info
  }

  override fun getKeysByPrefix(prefix: String, namespace: String): Set<String> {
    return delegate.getKeysByPrefix(prefix, namespace)
  }

  override fun getKeysByPrefix(prefix: String): Set<String> {
    return delegate.getKeysByPrefix(prefix)
  }

  override fun getLong(key: String): Long {
    return delegate.getLong(key)
  }

  override fun getLong(key: String, namespace: String): Long {
    return delegate.getLong(key, namespace)
  }

  override fun getString(key: String): String {
    return delegate.getString(key)
  }

  override fun getString(key: String, namespace: String): String {
    return delegate.getString(key, namespace)
  }

  override fun getValue(key: String): FirebaseRemoteConfigValue {
    return delegate.getValue(key)
  }

  override fun getValue(key: String, namespace: String): FirebaseRemoteConfigValue {
    return delegate.getValue(key, namespace)
  }

  override fun rxFetch(cacheExpirationSeconds: Long): Completable {
    return Completable.create { emitter ->
      delegate.fetch(cacheExpirationSeconds)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxFetch()")
    }
  }

  override fun rxFetch(): Completable {
    return Completable.create { emitter ->
      delegate.fetch()
          .addRxOnCompleteListener(emitter, "Unexpected error in rxFetch()")
    }
  }

  override fun setConfigSettings(settings: FirebaseRemoteConfigSettings) {
    return delegate.setConfigSettings(settings)
  }

  override fun setDefaults(resourceId: Int) {
    return delegate.setDefaults(resourceId)
  }

  override fun setDefaults(defaults: Map<String,Any>, namespace: String) {
    return delegate.setDefaults(defaults, namespace)
  }

  override fun setDefaults(resourceId: Int, namespace: String) {
    return delegate.setDefaults(resourceId, namespace)
  }

  override fun setDefaults(defaults: Map<String,Any>) {
    return delegate.setDefaults(defaults)
  }
}