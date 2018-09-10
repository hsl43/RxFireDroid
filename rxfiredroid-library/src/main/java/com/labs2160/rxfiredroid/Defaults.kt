package com.labs2160.rxfiredroid

import io.reactivex.BackpressureStrategy

abstract class Defaults {
  companion object {
    val BACKPRESSURE_STRATEGY = BackpressureStrategy.LATEST
  }
}