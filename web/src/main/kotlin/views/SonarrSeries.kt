package views

import com.brandontalbot.djserver.sonarr.model.Series
import components.SeriesCard
import csstype.*
import emotion.react.css
import getServerAddress
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import mainScope
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.useEffectOnce
import react.useState

suspend fun getSonarrSeries(): List<Series> {
    val response = window.fetch("${getServerAddress()}/sonarr/series")
        .await()
        .text()
        .await()
    return Json.decodeFromString(Json.serializersModule.serializer(), response)
}

val SonarrSeries = FC<Props> {
    var series: List<Series> by useState(listOf())
    var loading: Boolean by useState(true)

    useEffectOnce {
        mainScope.launch {
            series = getSonarrSeries()
            loading = false
        }
    }

    if (loading) {
        h1 { +"Loading..." }
    } else {
        div {
            css {
                display = Display.grid
                gap = 0.8.rem
                gridTemplateColumns = repeat(AutoRepeat.autoFit, minmax(320.px, 1.fr))

                "@media only screen and (min-width: 768px)" {
                    gridTemplateColumns = repeat(AutoRepeat.autoFit, minmax(240.px, 1.fr))
                }
                "@media only screen and (min-width: 992px)" {
                    gridTemplateColumns = repeat(AutoRepeat.autoFit, minmax(200.px, 1.fr))
                }
            }
            if (series.isEmpty()) {
                h1 { +"Nothing to see here!" }
            }
            series.forEach {
                SeriesCard {
                    this.series = it
                }
            }
        }
    }
}
