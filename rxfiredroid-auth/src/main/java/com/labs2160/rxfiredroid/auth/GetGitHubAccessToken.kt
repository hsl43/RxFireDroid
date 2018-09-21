package com.labs2160.rxfiredroid.auth

import org.json.JSONObject
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Callable
import javax.net.ssl.HttpsURLConnection

internal class GetGitHubAccessToken(
    private val configuration: GitHubAuthConfiguration,
    private val tempToken: GitHubTempToken

) : Callable<GitHubAccessTokenState> {

  override fun call(): GitHubAccessTokenState {
    val url = URL(StringBuilder("https://github.com/login/oauth/access_token")
        .append("?client_id=").append(configuration.clientId)
        .append("&client_secret=").append(configuration.clientSecret)
        .append("&code=").append(tempToken.token)
        .append("&redirect_uri=").append(configuration.callbackUrl)
        .append("&state=").append(tempToken.state)
        .toString()
    )

    var connection: HttpsURLConnection? = null
    var stream: InputStream? = null
    var state: GitHubAccessTokenState

    try {
      connection = (url.openConnection() as HttpsURLConnection).apply {
        readTimeout    = 10000
        connectTimeout = 15000
        requestMethod  = "POST"
        doInput        = true
        doOutput       = true

        setRequestProperty("Accept", "application/json")
      }

      connection.connect()

      val statusCode = connection.responseCode

      state = if(statusCode == HttpsURLConnection.HTTP_OK) {
        stream = connection.inputStream

        val json = JSONObject(stream.bufferedReader().use { it.readText() })

        GitHubAccessTokenState(
            token = GitHubAccessToken(
                accessToken = json.getString("access_token"),
                scope = json.getString("scope"),
                tokenType = json.getString("token_type")
            ))

      } else {
        GitHubAccessTokenState(errorMessage = "Response for access token had status code [$statusCode]")
      }

    } catch(t: Throwable) {
      state = GitHubAccessTokenState(errorCause = t)

    } finally {
      stream?.close()
      connection?.disconnect()
    }

    return state
  }
}