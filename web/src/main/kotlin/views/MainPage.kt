package views

import csstype.Color
import emotion.react.Global
import emotion.react.styles
import react.FC
import react.Props
import react.createElement
import react.dom.html.ReactHTML.body
import react.router.Route
import react.router.Routes
import react.router.dom.HashRouter

val MainPage = FC<Props> {
    Global {
        styles {
            body {
                backgroundColor = Color("#151515")
            }
        }
    }
    HashRouter {
        Routes {
            Route {
                index = true
                element = createElement(SonarrSeries)
            }
            Route {
                path = "/sonarr/series/:seriesId"
                element = createElement(SonarrSeriesDetails)
            }
        }
    }
}