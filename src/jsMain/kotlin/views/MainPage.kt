package views

import react.FC
import react.Props
import react.dom.html.ReactHTML.h1

val MainPage = FC<Props> {
    h1 { +"Test" }
    SonarrSeries()
}