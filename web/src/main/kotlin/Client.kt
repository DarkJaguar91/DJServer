import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import react.create
import react.dom.client.createRoot
import views.MainPage

val mainScope = MainScope()

fun main() {
    val root = createRoot(document.getElementById("root") ?: error("[root] div not found"))
    root.render(MainPage.create())
}
