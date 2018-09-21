package com.labs2160.rxfiredroid.messaging

import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.Completable
import com.google.firebase.messaging.FirebaseMessaging as GoogleFirebaseMessaging

interface FirebaseMessaging {
  companion object {
    private val lock = Any()

    private var instance: FirebaseMessagingImpl? = null

    fun getInstance(): FirebaseMessaging {
      synchronized(lock) {
        if(instance == null) {
          instance = FirebaseMessagingImpl(GoogleFirebaseMessaging.getInstance())
        }
      }

      return instance!!
    }
  }

  /**
   * Determine whether FCM auto-initialization is enabled or disabled.
   */
  fun isAutoInitEnabled(): Boolean

  /**
   * Subscribes to topic in the background.
   */
  fun rxSubscribeToTopic(topic: String): Completable

  /**
   * Unsubscribes from topic in the background.
   */
  fun rxUnsubscribeFromTopic(topic: String): Completable

  /**
   * Sends [message] upstream to your app server.
   */
  fun send(message: RemoteMessage)

  /**
   * Enable or disable auto-initialization of Firebase Cloud Messaging.
   */
  fun setAutoInitEnabled(enable: Boolean)

  /**
   * Subscribes to [topic] in the background.
   */
  fun subscribeToTopic(topic: String): Task<Void>

  /**
   * Unsubscribes from [topic] in the background.
   */
  fun unsubscribeFromTopic(topic: String): Task<Void>
}