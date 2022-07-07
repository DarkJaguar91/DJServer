package com.brandontalbot.djserver

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.web() {
    routing {
        static {
            resources()
            defaultResource("index.html")
        }
    }
}