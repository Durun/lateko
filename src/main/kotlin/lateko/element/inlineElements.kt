package lateko.element

import lateko.dsl.inline.text
import lateko.visitor.InlineVisitor
import java.net.URL


data class Text(val text: String) : InlineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
}

data class UrlText(val url: URL, val text: InlineElement) : InlineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)

	companion object {
		fun of(url: String, text: InlineElement? = null): UrlText {
			val urlObject = URL(url)
			return UrlText(
					url = urlObject,
					text = text ?: urlObject.toString().text)
		}

		fun of(url: String, text: String): UrlText = of(url = url, text = text.text)
	}
}

data class EmbeddedCode(val code: String, val format: Format): InlineElement {
	enum class Format {
		Markdown,
		Tex
	}
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
}