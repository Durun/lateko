package io.github.durun.lateko.model.inline

data class EmbeddedCode(val code: String, val format: Format) : InlineElement {
	enum class Format {
		Markdown,
		Tex
	}

	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
}