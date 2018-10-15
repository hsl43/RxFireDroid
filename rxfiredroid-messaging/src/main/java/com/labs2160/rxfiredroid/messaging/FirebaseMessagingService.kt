package com.labs2160.rxfiredroid.messaging

import com.google.firebase.messaging.FirebaseMessagingService as GoogleFirebaseMessagingService

abstract class FirebaseMessagingService : GoogleFirebaseMessagingService() {
  override fun onNewToken(token: String) = Streams.onNewToken(token)
}