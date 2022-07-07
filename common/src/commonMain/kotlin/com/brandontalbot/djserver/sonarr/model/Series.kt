package com.brandontalbot.djserver.sonarr.model

import kotlinx.serialization.Serializable

private val imageRegex = Regex("""(poster|banner|fanart)\.(\w+)""")

private fun String.setImageSize(size: Int) =
    this.replace(imageRegex) {
        "${it.groupValues[1]}-$size.${it.groupValues[2]}"
    }

@Serializable
data class Series(
    val id: Long,
    val title: String,
    val sortTitle: String,
    val images: List<SeriesImage>,
) {
    val poster: String?
        get() = images.find { it.coverType == "poster" }?.url

    val poster250: String?
        get() = poster?.setImageSize(250)

    val poster500: String?
        get() = poster?.setImageSize(500)


    val banner: String?
        get() = images.find { it.coverType == "banner" }?.url

    val banner35: String?
        get() = banner?.setImageSize(35)

    val banner70: String?
        get() = banner?.setImageSize(70)


    val fanart: String?
        get() = images.find { it.coverType == "fanart" }?.url

    val fanart180: String?
        get() = fanart?.setImageSize(180)

    val fanart360: String?
        get() = fanart?.setImageSize(360)
}