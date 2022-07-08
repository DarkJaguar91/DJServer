package views

import com.brandontalbot.djserver.sonarr.model.Series
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
import react.dom.html.ReactHTML.img
import react.router.useParams
import react.useEffect
import react.useState

suspend fun getSeries(id: Long) = window.fetch("${getServerAddress()}/sonarr/series/$id")
    .await()
    .text()
    .await()
    .let {
        Json.decodeFromString<Series>(Json.serializersModule.serializer(), it)
    }

val SonarrSeriesDetails = FC<Props> {
    val seriesId = useParams()["seriesId"]
    var seriesState: Series? by useState(null)

    useEffect(seriesId) {
        mainScope.launch {
            seriesId?.let {
                seriesState = getSeries(it.toLong())
            }
        }
    }

    seriesState?.let { series ->
        div {
            css {
                position = Position.relative
            }
            img {
                css {
                    width = 100.pct
                    filter = brightness(40.pct)
                }
                src = series.fanart
            }
            img {
                css {
                    width = 250.px
                    position = Position.absolute
                    marginTop = 1.rem
                    marginLeft = 1.rem
                    top = 0.px
                    left = 0.px
                }
                src = series.poster250
            }
        }
    } ?: run {
        h1 { +"Loading" }
    }
}
