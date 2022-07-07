package com.brandontalbot.djserver.sonarr.model

import kotlinx.serialization.Serializable

@Serializable
data class Series(
    val id: Long,
    val title: String,
    val sortTitle: String,
    val images: Set<SeriesImage>,
)
