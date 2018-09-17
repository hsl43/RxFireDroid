package com.labs2160.rxfiredroid.auth

internal data class GitHubTempToken(
    val token: String,
    val state: String
)