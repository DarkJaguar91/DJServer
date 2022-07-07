package com.brandontalbot.djserver.config

import io.ktor.http.*

open class EnvConfig {
    open val host: Url by lazy {
        System.getenv("HOST_URL")?.let {
            Url(it)
        } ?: error("Host URL required")
    }
}