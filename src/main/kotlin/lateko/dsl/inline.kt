package lateko.dsl

import lateko.element.EmbeddedCode
import lateko.element.InlineElement
import lateko.element.Text
import lateko.element.UrlText

fun String.asMarkdown(): EmbeddedCode = this.embed(EmbeddedCode.Format.Markdown)
fun String.asTex(): EmbeddedCode = this.embed(EmbeddedCode.Format.Tex)
fun String.embed(format: EmbeddedCode.Format): EmbeddedCode {
	return EmbeddedCode(format = format, code = this)
}

val String.text: Text get() = Text(this)

fun InlineElement.url(url: String): UrlText = UrlText.of(url = url, text = this)
fun String.url(url: String): UrlText = this.text.url(url)
val String.url: UrlText get() = this.text.url(this)