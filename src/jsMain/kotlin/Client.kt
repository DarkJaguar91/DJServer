import kotlinx.browser.document
import react.create
import react.dom.client.createRoot
import views.MainPage

fun main() {
    val root = createRoot(document.getElementById("root") ?: error("[root] div not found"))
    root.render(MainPage.create())
}
