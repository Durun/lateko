package lateko.dsl

import lateko.element.*

operator fun InlineElement.minus(other: InlineElement): InlineElement {
	return InlineComposition.of(this, other)
}

operator fun InlineElement.minus(other: String): InlineElement {
	return this - other.text
}

operator fun String.minus(other: InlineElement): InlineElement {
	return this.text - other
}

@Deprecated("Using + is deprecated.", replaceWith = ReplaceWith("this - other", "lateko.dsl.minus"))
operator fun InlineElement.plus(other: InlineElement): InlineElement = this - other
@Deprecated("Using + is deprecated.", replaceWith = ReplaceWith("this - other", "lateko.dsl.minus"))
operator fun InlineElement.plus(other: String): InlineElement = this - other

fun String.asMarkdown(): EmbeddedCode = this.embed(EmbeddedCode.Format.Markdown)
fun String.asTex(): EmbeddedCode = this.embed(EmbeddedCode.Format.Tex)
fun String.embed(format: EmbeddedCode.Format): EmbeddedCode {
	return EmbeddedCode(format = format, code = this)
}

val String.text: Text get() = Text(this)

fun InlineElement.url(url: String): UrlText = UrlText.of(url = url, text = this)
fun String.url(url: String): UrlText = this.text.url(url)
val String.url: UrlText get() = this.text.url(this)