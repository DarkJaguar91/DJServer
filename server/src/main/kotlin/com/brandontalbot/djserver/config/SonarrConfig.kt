package com.brandontalbot.djserver.config

import com.brandontalbot.djserver.sonarr.SonarrClient
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

open class SonarrConfig {
    open val httpClient: HttpClient by lazy {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }

    open val token: String by lazy {
        System.getenv("SONARR_TOKEN") ?: error("No Sonarr token provided.")
    }

    open val url: Url by lazy {
        System.getenv("SONARR_URL")?.let {
            Url(it)
        } ?: error("No Sonarr token provided.")
    }

    open val sonarrClient: SonarrClient by lazy {
        SonarrClient(httpClient, token, url)
    }
}
