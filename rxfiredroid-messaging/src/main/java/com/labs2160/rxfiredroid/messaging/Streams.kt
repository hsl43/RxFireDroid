package com.labs2160.rxfiredroid.messaging

import com.google.firebase.iid.FirebaseInstanceId
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

internal object Streams {
  private val registrationTokenChannel by lazy { BehaviorSubject.create<String>() }

  fun onNewToken(token: String) = registrationTokenChannel.onNext(token)

  fun bindRegistrationToken(backpressureStrategy: BackpressureStrategy): Flowable<String> {
    return if(registrationTokenChannel.value != null) {
      registrationTokenChannel.toFlowable(backpressureStrategy)
    } else {
      val instanceIdTokenSingle = Single.create<String> { emitter ->
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task ->
              if(task.isSuccessful) {
                val token = task.result?.token

                if(token != null) {
                  emitter.onSuccess(token)
                } else {
                  emitter.onError(RuntimeException("Task completed but did not produce a result"))
                }

              } else {
                emitter.onError(RuntimeException("Unexpected error while resolving token from FirebaseInstanceId"))
              }
            }
      }

      instanceIdTokenSingle
          .toFlowable()
          .mergeWith(registrationTokenChannel.toFlowable(backpressureStrategy))
    }
  }
}