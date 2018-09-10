package com.labs2160.rxfiredroid.auth

internal data class GitHubAccessToken(
    val accessToken: String,
    val scope: String? = null,
    val tokenType: String? = null
)