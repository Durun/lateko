package lateko.dsl.inline

import lateko.model.inline.InlineElement
import lateko.model.inline.StyledText
import lateko.model.inline.UrlText


fun InlineElement.url(url: String): UrlText = UrlText(url = url, text = this)
fun String.url(url: String): UrlText = this.text.url(url)
val String.url: UrlText get() = this.text.url(this)
val StyledText.url: UrlText get() = url(url = string)