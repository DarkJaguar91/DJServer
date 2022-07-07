package com.brandontalbot.djserver

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import kotlinx.serialization.json.Json

fun Application.configure() {
    install(CORS) {
        anyHost()
    }
    install(ContentNegotiation) {
        json(
            Json {
                isLenient = true
            }
        )
    }
}