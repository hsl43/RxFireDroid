package com.labs2160.rxfiredroid

import android.app.Activity
import com.google.android.gms.tasks.*
import io.reactivex.Completable
import java.util.concurrent.Executor
import com.google.android.gms.tasks.Task as GoogleTask

interface Task<TResult> {
  companion object {
    fun <TResult> newInstance(googleTask: GoogleTask<TResult>): Task<TResult> {
      return TaskImpl(googleTask)
    }
  }

  /**
   * Adds a listener that is called if the Task is canceled.
   */
  fun addOnCanceledListener(listener: OnCanceledListener): GoogleTask<TResult>

  /**
   * Adds a listener that is called if the Task is canceled.
   */
  fun addOnCanceledListener(executor: Executor, listener: OnCanceledListener): GoogleTask<TResult>

  /**
   * Adds an Activity-scoped listener that is called if the Task is canceled.
   */
  fun addOnCanceledListener(activity: Activity, listener: OnCanceledListener): GoogleTask<TResult>

  /**
   * Adds a listener that is called when the Task completes.
   */
  fun addOnCompleteListener(listener: OnCompleteListener<TResult>): GoogleTask<TResult>

  /**
   * Adds an Activity-scoped listener that is called when the Task completes.
   */
  fun addOnCompleteListener(activity: Activity, listener: OnCompleteListener<TResult>): GoogleTask<TResult>

  /**
   * Adds a listener that is called when the Task completes.
   */
  fun addOnCompleteListener(executor: Executor, listener: OnCompleteListener<TResult>): GoogleTask<TResult>

  /**
   * Adds an Activity-scoped listener that is called if the Task fails.
   */
  fun addOnFailureListener(activity: Activity, listener: OnFailureListener): GoogleTask<TResult>

  /**
   * Adds a listener that is called if the Task fails.
   */
  fun addOnFailureListener(listener: OnFailureListener): GoogleTask<TResult>

  /**
   * Adds a listener that is called if the Task fails.
   */
  fun addOnFailureListener(executor: Executor, listener: OnFailureListener): GoogleTask<TResult>

  /**
   * Adds a listener that is called if the Task completes successfully.
   */
  fun addOnSuccessListener(executor: Executor, listener: OnSuccessListener<in TResult>): GoogleTask<TResult>

  /**
   * Adds a listener that is called if the Task completes successfully.
   */
  fun addOnSuccessListener(listener: OnSuccessListener<in TResult>): GoogleTask<TResult>

  /**
   * Adds an Activity-scoped listener that is called if the Task completes
   * successfully.
   */
  fun addOnSuccessListener(activity: Activity, listener: OnSuccessListener<in TResult>): GoogleTask<TResult>

  /**
   * Returns a new Task that will be completed with the result of applying the
   * specified Continuation to this Task.
   */
  fun <TContinuationResult> continueWith(
      continuation: Continuation<TResult,TContinuationResult>

  ): GoogleTask<TContinuationResult>

  /**
   * Returns a new Task that will be completed with the result of applying the
   * specified Continuation to this Task.
   */
  fun <TContinuationResult> continueWith(
      executor: Executor,
      continuation: Continuation<TResult,TContinuationResult>

  ): GoogleTask<TContinuationResult>

  /**
   * Returns a new Task that will be completed with the result of applying the
   * specified Continuation to this Task.
   */
  fun <TContinuationResult> continueWithTask(
      continuation: Continuation<TResult,GoogleTask<TContinuationResult>>

  ): GoogleTask<TContinuationResult>

  /**
   * Returns a new Task that will be completed with the result of applying the
   * specified Continuation to this Task.
   */
  fun <TContinuationResult> continueWithTask(
      executor: Executor,
      continuation: Continuation<TResult,GoogleTask<TContinuationResult>>

  ): GoogleTask<TContinuationResult>

  /**
   * Returns the exception that caused the Task to fail.
   */
  fun getException(): Exception?

  /**
   * Gets the result of the Task, if it has already completed.
   */
  fun getResult(): TResult

  /**
   * Gets the result of the Task, if it has already completed.
   */
  fun <X : Throwable> getResult(exceptionType: Class<X>): TResult

  /**
   * Returns true if the Task is canceled; false otherwise.
   */
  fun isCanceled(): Boolean

  /**
   * Returns true if the Task is complete; false otherwise.
   */
  fun isComplete(): Boolean

  /**
   * Returns true if the Task has completed successfully; false otherwise.
   */
  fun isSuccessful(): Boolean

  /**
   * Returns a new Task that will be completed with the result of applying the
   * specified SuccessContinuation to this Task when this Task completes successfully.
   */
  fun <TContinuationResult> onSuccessTask(
      executor: Executor,
      successContinuation: SuccessContinuation<TResult,TContinuationResult>

  ): GoogleTask<TContinuationResult>

  /**
   * Returns a new Task that will be completed with the result of applying the
   * specified SuccessContinuation to this Task when this Task completes successfully.
   */
  fun <TContinuationResult> onSuccessTask(
      successContinuation: SuccessContinuation<TResult,TContinuationResult>

  ): GoogleTask<TContinuationResult>

  /**
   * Observe the Task's completion.
   */
  fun rxBindState(): Completable

  /**
   * Observe the Task's completion.
   */
  fun rxBindState(executor: Executor): Completable

  /**
   * Observe the Task's completion.
   */
  fun rxBindState(activity: Activity): Completable
}