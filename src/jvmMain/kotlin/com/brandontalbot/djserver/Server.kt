package com.brandontalbot.djserver

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun Application.configure() {
    install(ContentNegotiation) {
        json(
            Json {
                isLenient = true
            }
        )
    }
}