package lateko.dsl.inline

import lateko.element.InlineElement
import lateko.element.UrlText


fun InlineElement.url(url: String): UrlText = UrlText.of(url = url, text = this)
fun String.url(url: String): UrlText = this.text.url(url)
val String.url: UrlText get() = this.text.url(this)