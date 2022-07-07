package components

import com.brandontalbot.djserver.sonarr.model.Series
import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ImgLoading
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.picture
import react.dom.html.ReactHTML.source
import react.router.useNavigate
import util.sonarrTempImage


external interface SeriesCardProps : Props {
    var series: Series
}

val SeriesCard = FC<SeriesCardProps> { props ->
    val navigate = useNavigate()
    val imageUrl = props.series.poster500

    div {
        css {
            position = Position.relative
            overflow = Overflow.hidden
            borderRadius = 4.px
            transitionDuration = 250.ms
            hover {
                boxShadow = BoxShadow(0.px, 0.px, 12.px, Color("#000"))
                ".titleText" {
                    opacity = number(1.0)
                    transitionProperty = TransitionProperty.all
                    transitionDuration = 250.ms
                    transitionTimingFunction = TransitionTimingFunction.easeInOut
                }
            }
        }
        onClick = {
            navigate("/sonarr/series/${props.series.id}")
        }
        picture {
            if (props.series.poster != null) {
                source {
                    media = "(max-width: 768px)"
                    srcSet = "${props.series.poster250}"
                }
                source {
                    media = "(min-width: 992px)"
                    srcSet = "${props.series.poster500}"
                }
                source {
                    media = "(min-width: 1200px)"
                    srcSet = "${props.series.poster}"
                }
            }
            img {
                css {
                    display = Display.block
                    width = 100.pct
                }
                this.src = sonarrTempImage
                this.loading = ImgLoading.lazy
            }
        }
        div {
            css(ClassName("titleText")) {
                position = Position.absolute
                bottom = 0.px
                width = 100.pct
                zIndex = integer(2)
                backgroundColor = Color("#00000088")
                color = Color("white")
                textAlign = TextAlign.center
                padding = 4.px
                opacity = if (imageUrl != null) number(0.0) else number(1.0)
            }
            p {
                css {
                    margin = 0.px
                }
                +props.series.title
            }
        }
    }
}
