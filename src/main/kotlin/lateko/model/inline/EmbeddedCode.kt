package lateko.model.inline

import lateko.model.InlineElement
import lateko.visitor.InlineVisitor

data class EmbeddedCode(val code: String, val format: Format) : InlineElement {
	enum class Format {
		Markdown,
		Tex
	}

	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
}