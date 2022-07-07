package com.brandontalbot.djserver.server

import com.brandontalbot.djserver.sonarr.model.Series
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class DjServerClient(
    private val client: HttpClient,
    private val serverUrl: Url,
) {
    suspend fun sonarrSeries(): List<Series> = get("sonarr", "series").body()

    private suspend inline fun get(vararg path: String) =
        client.get {
            url {
                takeFrom(serverUrl)
                appendPathSegments(path.toList())
            }
        }
}