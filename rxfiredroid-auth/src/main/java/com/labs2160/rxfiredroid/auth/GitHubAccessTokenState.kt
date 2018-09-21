package com.labs2160.rxfiredroid.auth

internal data class GitHubAccessTokenState(
    val token: GitHubAccessToken? = null,
    val errorMessage: String? = null,
    val errorCause: Throwable? = null
)