package com.labs2160.rxfiredroid.auth

class GoogleAuthConfiguration(
    val requestIdToken: String,
    val requestId: Boolean = false,
    val requestEmail: Boolean = false,
    val requestProfile: Boolean = false
)