package com.labs2160.rxfiredroid.auth

import com.google.firebase.auth.AuthCredential

internal data class GoogleAuthResult(
    val credential: AuthCredential? = null,
    val error: Throwable? = null
)