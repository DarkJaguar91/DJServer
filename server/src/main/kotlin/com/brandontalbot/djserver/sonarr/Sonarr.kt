package com.brandontalbot.djserver.sonarr

import com.brandontalbot.djserver.config.Context
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import io.ktor.utils.io.*

fun Application.sonarr(
    sonarrClient: SonarrClient = Context.sonarrConfig.sonarrClient,
    host: Url = Context.envConfig.host,
) {
    routing {
        route("sonarr") {
            get("series") {
                call.respond(sonarrClient.series().map { series ->
                    series.copy(images = series.images.map {
                        it.copy(url = "$host/sonarr${it.url}")
                    })
                }.sortedBy { it.sortTitle })
            }
            get("series/{id}") {
                call.respond(sonarrClient.series(call.parameters["id"]!!.toLong()).let { series ->
                    series.copy(images = series.images.map {
                        it.copy(url = "$host/sonarr${it.url}")
                    })
                })
            }
            get("/MediaCover/{id}/{type}") {
                val id = call.parameters["id"]!!.toLong()
                val type = call.parameters["type"]!!

                val lastWrite = call.request.queryParameters["lastWrite"]

                val response = sonarrClient.getImage(id, type, lastWrite)

                proxyResponse(response)
            }
        }
    }
}

@OptIn(InternalAPI::class)
suspend fun PipelineContext<*, ApplicationCall>.proxyResponse(response: HttpResponse) {
    val proxiedHeaders = response.headers
    val contentType = proxiedHeaders[HttpHeaders.ContentType]
    val contentLength = proxiedHeaders[HttpHeaders.ContentLength]

    // Proxy the request forward
    call.respond(object : OutgoingContent.WriteChannelContent() {
        override val contentLength: Long? = contentLength?.toLong()
        override val contentType: ContentType? = contentType?.let { ContentType.parse(it) }
        override val headers: Headers = Headers.build {
            appendAll(proxiedHeaders.filter { key, _ ->
                !key.equals(
                    HttpHeaders.ContentType,
                    ignoreCase = true
                ) && !key.equals(HttpHeaders.ContentLength, ignoreCase = true)
                        && !key.equals(HttpHeaders.TransferEncoding, ignoreCase = true)
            })
        }
        override val status: HttpStatusCode = response.status
        override suspend fun writeTo(channel: ByteWriteChannel) {
            response.content.copyAndClose(channel)
        }
    })
}