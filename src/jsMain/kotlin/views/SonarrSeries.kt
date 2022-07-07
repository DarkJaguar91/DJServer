package views

import com.brandontalbot.djserver.sonarr.model.Series
import csstype.*
import emotion.react.css
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import react.FC
import react.Props
import react.dom.html.ImgLoading
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.p
import react.useEffectOnce
import react.useState

val mainScope = MainScope()

suspend fun getSonarrSeries(): List<Series> {
    val response = window.fetch("/sonarr/series")
        .await()
        .text()
        .await()
    return Json.decodeFromString(Json.serializersModule.serializer(), response)
}

val SonarrSeries = FC<Props> {
    var series: List<Series> by useState(listOf())

    useEffectOnce {
        mainScope.launch {
            series = getSonarrSeries()
        }
    }

    div {
        css {
            display = Display.grid
            gap = 0.5.rem
            gridTemplateColumns = repeat(AutoRepeat.autoFit, minmax(240.px, 1.fr))
        }
        series.forEach {
            img {
                css {
                    borderRadius = 8.px
                    width = 100.pct
                }
                src = it.images.find { it.coverType == "poster" }?.url?.replace("(\\.\\w+)\\?".toRegex()) {
                    "-250${it.groupValues[1]}?"
                }
                loading = ImgLoading.lazy
            }
        }
    }
}
