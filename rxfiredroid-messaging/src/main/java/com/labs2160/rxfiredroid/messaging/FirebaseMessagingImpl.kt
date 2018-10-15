package com.labs2160.rxfiredroid.messaging

import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.RemoteMessage
import com.labs2160.rxfiredroid.addRxOnCompleteListener
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import com.google.firebase.messaging.FirebaseMessaging as GoogleFirebaseMessaging

@Suppress("unused")
internal class FirebaseMessagingImpl(private val delegate: GoogleFirebaseMessaging) : FirebaseMessaging {
  override fun rxBindRegistrationToken(backpressureStrategy: BackpressureStrategy): Flowable<String> {
    return Streams.bindRegistrationToken(backpressureStrategy)
  }

  override fun isAutoInitEnabled(): Boolean {
    return delegate.isAutoInitEnabled
  }

  override fun rxSubscribeToTopic(topic: String): Completable {
    return Completable.create { emitter ->
      delegate.subscribeToTopic(topic)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxSubscribeToTopic()")
    }
  }

  override fun rxUnsubscribeFromTopic(topic: String): Completable {
    return Completable.create { emitter ->
      delegate.unsubscribeFromTopic(topic)
          .addRxOnCompleteListener(emitter, "Unexpected error in rxUnsubscribeFromTopic()")
    }
  }

  override fun send(message: RemoteMessage) {
    delegate.send(message)
  }

  override fun setAutoInitEnabled(enable: Boolean) {
    delegate.isAutoInitEnabled = enable
  }

  override fun subscribeToTopic(topic: String): Task<Void> {
    return delegate.subscribeToTopic(topic)
  }

  override fun unsubscribeFromTopic(topic: String): Task<Void> {
    return delegate.unsubscribeFromTopic(topic)
  }
}