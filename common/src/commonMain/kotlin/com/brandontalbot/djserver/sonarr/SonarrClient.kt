package com.brandontalbot.djserver.sonarr

import com.brandontalbot.djserver.sonarr.model.Series
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class SonarrClient(
    private val client: HttpClient,
    private val token: String,
    private val sonarrUrl: Url,
) {
    suspend fun series(): Set<Series> = get("series").body()

    suspend fun series(id: Long): Series = get("series", id.toString()).body()

    suspend fun getImage(
        id: Long,
        type: String,
        lastWrite: String? = null
    ): HttpResponse = get(
        "MediaCover", id.toString(), type,
        params = listOfNotNull(lastWrite?.let { "lastWrite" to it })
    )

    private suspend inline fun get(
        vararg path: String,
        params: List<Pair<String, Any>> = listOf()
    ) =
        client.get {
            url {
                takeFrom(sonarrUrl)
                appendPathSegments("api", "v3", *path)
                params.forEach {
                    parameters.append(it.first, it.second.toString())
                }
            }
            header("X-Api-Key", token)
        }
}