package com.brandontalbot.djserver.config

object Context {
    val sonarrConfig: SonarrConfig by lazy {
        SonarrConfig()
    }

    val envConfig: EnvConfig by lazy {
        EnvConfig()
    }
}