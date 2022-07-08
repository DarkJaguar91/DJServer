import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import react.create
import react.dom.client.createRoot
import views.MainPage

val mainScope = MainScope()

fun getServerAddress(): String =
    "${window.location.protocol}://${window.location.host}:${window.location.port}"

fun main() {
    val root = createRoot(document.getElementById("root") ?: error("[root] div not found"))
    root.render(MainPage.create())
}
