package com.labs2160.rxfiredroid.auth

import com.twitter.sdk.android.core.TwitterAuthToken
import com.twitter.sdk.android.core.TwitterException

internal data class TwitterAuthResult(
    val authToken: TwitterAuthToken? = null,
    val error: TwitterException? = null
)