package com.labs2160.rxfiredroid

import android.app.Activity
import com.google.android.gms.tasks.*
import io.reactivex.Completable
import java.util.concurrent.Executor
import com.google.android.gms.tasks.Task as GoogleTask

internal open class TaskImpl<TResult>(private val delegate: GoogleTask<TResult>) : Task<TResult> {
  override fun addOnCanceledListener(listener: OnCanceledListener): GoogleTask<TResult> {
    return delegate.addOnCanceledListener(listener)
  }

  override fun addOnCanceledListener(executor: Executor, listener: OnCanceledListener): GoogleTask<TResult> {
    return delegate.addOnCanceledListener(executor, listener)
  }

  override fun addOnCanceledListener(activity: Activity, listener: OnCanceledListener): GoogleTask<TResult> {
    return delegate.addOnCanceledListener(activity, listener)
  }

  override fun addOnCompleteListener(listener: OnCompleteListener<TResult>): GoogleTask<TResult> {
    return delegate.addOnCompleteListener(listener)
  }

  override fun addOnCompleteListener(activity: Activity, listener: OnCompleteListener<TResult>): GoogleTask<TResult> {
    return delegate.addOnCompleteListener(activity, listener)
  }

  override fun addOnCompleteListener(executor: Executor, listener: OnCompleteListener<TResult>): GoogleTask<TResult> {
    return delegate.addOnCompleteListener(executor, listener)
  }

  override fun addOnFailureListener(activity: Activity, listener: OnFailureListener): GoogleTask<TResult> {
    return delegate.addOnFailureListener(activity, listener)
  }

  override fun addOnFailureListener(listener: OnFailureListener): GoogleTask<TResult> {
    return delegate.addOnFailureListener(listener)
  }

  override fun addOnFailureListener(executor: Executor, listener: OnFailureListener): GoogleTask<TResult> {
    return delegate.addOnFailureListener(executor, listener)
  }

  override fun addOnSuccessListener(executor: Executor, listener: OnSuccessListener<in TResult>): GoogleTask<TResult> {
    return delegate.addOnSuccessListener(executor, listener)
  }

  override fun addOnSuccessListener(listener: OnSuccessListener<in TResult>): GoogleTask<TResult> {
    return delegate.addOnSuccessListener(listener)
  }

  override fun addOnSuccessListener(activity: Activity, listener: OnSuccessListener<in TResult>): GoogleTask<TResult> {
    return delegate.addOnSuccessListener(activity, listener)
  }

  override fun <TContinuationResult> continueWith(
      continuation: Continuation<TResult,TContinuationResult>

  ): GoogleTask<TContinuationResult> {

    return delegate.continueWith(continuation)
  }

  override fun <TContinuationResult> continueWith(
      executor: Executor,
      continuation: Continuation<TResult,TContinuationResult>

  ): GoogleTask<TContinuationResult> {

    return delegate.continueWith(executor, continuation)
  }

  override fun <TContinuationResult> continueWithTask(
      continuation: Continuation<TResult,GoogleTask<TContinuationResult>>

  ): GoogleTask<TContinuationResult> {

    return delegate.continueWithTask(continuation)
  }

  override fun <TContinuationResult> continueWithTask(
      executor: Executor,
      continuation: Continuation<TResult,GoogleTask<TContinuationResult>>

  ): GoogleTask<TContinuationResult> {

    return delegate.continueWithTask(executor, continuation)
  }

  override fun getException(): Exception? {
    return delegate.exception
  }

  override fun getResult(): TResult? {
    return delegate.result
  }

  override fun <X : Throwable> getResult(exceptionType: Class<X>): TResult? {
    return delegate.getResult(exceptionType)
  }

  override fun isCanceled(): Boolean {
    return delegate.isCanceled
  }

  override fun isComplete(): Boolean {
    return delegate.isComplete
  }

  override fun isSuccessful(): Boolean {
    return delegate.isSuccessful
  }

  override fun <TContinuationResult> onSuccessTask(
      executor: Executor,
      successContinuation: SuccessContinuation<TResult,TContinuationResult>

  ): GoogleTask<TContinuationResult> {

    return delegate.onSuccessTask(executor, successContinuation)
  }

  override fun <TContinuationResult> onSuccessTask(
      successContinuation: SuccessContinuation<TResult,TContinuationResult>

  ): GoogleTask<TContinuationResult> {

    return delegate.onSuccessTask(successContinuation)
  }

  override fun rxBindState(): Completable {
    return Completable.create { emitter ->
      delegate.addRxOnCompleteListener(emitter)
    }
  }

  override fun rxBindState(executor: Executor): Completable {
    return Completable.create { emitter ->
      val listener = OnCompleteListener<TResult> { task ->
        if(task.isSuccessful) {
          emitter.onComplete()
        } else {
          emitter.onError(task.exception ?: RuntimeException("Unexpected error in task execution"))
        }
      }

      delegate.addOnCompleteListener(executor, listener)
    }
  }

  override fun rxBindState(activity: Activity): Completable {
    return Completable.create { emitter ->
      val listener = OnCompleteListener<TResult> { task ->
        if(task.isSuccessful) {
          emitter.onComplete()
        } else {
          emitter.onError(task.exception ?: RuntimeException("Unexpected error in task execution"))
        }
      }

      delegate.addOnCompleteListener(activity, listener)
    }
  }
}