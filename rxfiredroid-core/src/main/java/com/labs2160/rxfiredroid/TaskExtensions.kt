package com.labs2160.rxfiredroid

import com.google.android.gms.tasks.Task
import io.reactivex.CompletableEmitter
import io.reactivex.SingleEmitter

fun <TResult> Task<TResult>.addRxOnCompleteListener(emitter: CompletableEmitter, errorMessage: String? = null): Task<TResult> {
  this.addOnCompleteListener { task ->
    if(task.isSuccessful) {
      emitter.onComplete()
    } else {
      emitter.onError(task.exception ?: RuntimeException(errorMessage))
    }
  }

  return this
}

fun <TResult> Task<TResult>.addRxOnCompleteListener(emitter: SingleEmitter<TResult>, errorMessage: String? = null): Task<TResult> {
  this.addOnCompleteListener { task ->
    if(task.isSuccessful) {
      emitter.onSuccess(task.result)
    } else {
      emitter.onError(task.exception ?: RuntimeException(errorMessage))
    }
  }

  return this
}