package com.labs2160.rxfiredroid.samples

import android.util.Log
import com.google.firebase.messaging.RemoteMessage
import com.labs2160.rxfiredroid.messaging.FirebaseMessagingService

class SimpleFirebaseMessagingService : FirebaseMessagingService() {
  override fun onDeletedMessages() {
    Log.d(javaClass.name, "## beginning onDeletedMessages()")
  }

  override fun onMessageReceived(message: RemoteMessage?) {
    Log.d(javaClass.name, "## beginning onMessageReceived(${message?.messageId})")
    Log.d(javaClass.name, "## data == ${message?.data}")
  }

  override fun onMessageSent(msgId: String) {
    Log.d(javaClass.name, "## beginning onMessageSent($msgId)")
  }

  override fun onNewToken(token: String) {
    super.onNewToken(token)

    Log.d(javaClass.name, "## beginning onNewToken($token)")
  }
}