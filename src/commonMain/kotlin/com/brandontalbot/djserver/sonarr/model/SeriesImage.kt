package com.brandontalbot.djserver.sonarr.model

@kotlinx.serialization.Serializable
data class SeriesImage(
    val coverType: String,
    val url: String,
    val remoteUrl: String,
)
