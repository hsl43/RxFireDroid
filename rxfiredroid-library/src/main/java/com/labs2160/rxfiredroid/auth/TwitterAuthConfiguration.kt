package com.labs2160.rxfiredroid.auth

import android.util.Log

class TwitterAuthConfiguration(
    val consumerKey: String,
    val consumerSecret: String,
    val debug: Boolean = true,
    val logLevel: Int = Log.DEBUG
)