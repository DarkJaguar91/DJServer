package com.brandontalbot.djserver

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun Application.web() {
    routing {
        get("/") {
            call.respondHtml {
                head {
                    title("Brandon Talbot")
                }
                body {
                    div { id = "root" }
                    script(src = "/static/DJServer.js") {}
                }
            }
        }
        static("/static") {
            resources()
        }
    }
}