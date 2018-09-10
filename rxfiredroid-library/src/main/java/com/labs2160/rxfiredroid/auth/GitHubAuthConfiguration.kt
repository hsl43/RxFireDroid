package com.labs2160.rxfiredroid.auth

class GitHubAuthConfiguration(
    val clientId: String,
    val clientSecret: String,
    val callbackUrl: String,
    val scope: String? = null
)