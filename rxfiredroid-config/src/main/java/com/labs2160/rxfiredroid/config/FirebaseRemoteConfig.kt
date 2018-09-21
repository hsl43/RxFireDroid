package com.labs2160.rxfiredroid.config

import com.google.android.gms.tasks.Task
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
import io.reactivex.Completable
import com.google.firebase.remoteconfig.FirebaseRemoteConfig as GoogleFirebaseRemoteConfig

interface FirebaseRemoteConfig {
  companion object {
    private val lock = Any()

    private var instance: FirebaseRemoteConfigImpl? = null

    fun getInstance(): FirebaseRemoteConfig {
      synchronized(lock) {
        if(instance == null) {
          instance = FirebaseRemoteConfigImpl(GoogleFirebaseRemoteConfig.getInstance())
        }
      }

      return instance!!
    }
  }

  /**
   * Activates the Fetched Config, so that the fetched key-values take effect.
   */
  fun activateFetched(): Boolean

  /**
   * Fetches parameter values for your app.
   */
  fun fetch(cacheExpirationSeconds: Long): Task<Void>

  /**
   * Fetches parameter values for your app.
   */
  fun fetch(): Task<Void>

  /**
   * Gets the value corresponding to the specified key, as a boolean, in the
   * specified namespace.
   */
  fun getBoolean(key: String, namespace: String): Boolean

  /**
   * Gets the value corresponding to the specified key, as a boolean.
   */
  fun getBoolean(key: String): Boolean

  /**
   * Gets the value corresponding to the specified key, in the specified
   * namespace, as a byte array.
   */
  fun getByteArray(key: String, namespace: String): ByteArray

  /**
   * Gets the value corresponding to the specified key, as a byte array.
   */
  fun getByteArray(key: String): ByteArray

  /**
   * Gets the value corresponding to the specified key, in the specified
   * namespace, as a double.
   */
  fun getDouble(key: String, namespace: String): Double

  /**
   * Gets the value corresponding to the specified key, as a double.
   */
  fun getDouble(key: String): Double

  /**
   * Gets the current state of the FirebaseRemoteConfig singleton object.
   */
  fun getInfo(): FirebaseRemoteConfigInfo

  /**
   * Gets the set of keys that start with the given prefix, in the given
   * namespace.
   */
  fun getKeysByPrefix(prefix: String, namespace: String): Set<String>

  /**
   * Gets the set of keys that start with the given prefix.
   */
  fun getKeysByPrefix(prefix: String): Set<String>

  /**
   * Gets the value corresponding to the specified key, as a long.
   */
  fun getLong(key: String): Long

  /**
   * Gets the value corresponding to the specified key, in the specified
   * namespace, as a long.
   */
  fun getLong(key: String, namespace: String): Long

  /**
   * Gets the value corresponding to the specified key, as a String.
   */
  fun getString(key: String): String

  /**
   * Gets value as a string corresponding to the specified key in the specified
   * namespace.
   */
  fun getString(key: String, namespace: String): String

  /**
   * Gets the FirebaseRemoteConfigValue corresponding to the specified key.
   */
  fun getValue(key: String): FirebaseRemoteConfigValue

  /**
   * Gets the FirebaseRemoteConfigValue corresponding to the specified key.
   */
  fun getValue(key: String, namespace: String): FirebaseRemoteConfigValue

  /**
   * Fetches parameter values for your app.
   */
  fun rxFetch(cacheExpirationSeconds: Long): Completable

  /**
   * Fetches parameter values for your app.
   */
  fun rxFetch(): Completable

  /**
   * Changes the settings for the FirebaseRemoteConfig object's operations,
   * such as turning the developer mode on.
   */
  fun setConfigSettings(settings: FirebaseRemoteConfigSettings)

  /**
   * Sets defaults in the default namespace, using an XML resource.
   */
  fun setDefaults(resourceId: Int)

  /**
   * Sets defaults in the default namespace.
   */
  fun setDefaults(defaults: Map<String,Any>, namespace: String)

  /**
   * Set defaults in the given namespace, using an XML resource file.
   */
  fun setDefaults(resourceId: Int, namespace: String)

  /**
   * Sets defaults in the default namespace.
   */
  fun setDefaults(defaults: Map<String,Any>)
}