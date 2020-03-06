package lateko.model.inline

import lateko.dsl.inline.text
import lateko.model.InlineElement
import lateko.visitor.InlineVisitor

data class UrlText(
		val url: String,
		val text: InlineElement = url.text
) : InlineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
}

