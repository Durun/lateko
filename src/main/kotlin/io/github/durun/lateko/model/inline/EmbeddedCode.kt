package io.github.durun.lateko.model.inline

data class EmbeddedCode(val code: String, val format: Format) : InlineElement {
	enum class Format {
		Markdown,
		Tex,
		None
	}

	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
	override fun renderedAs(format: Format): String {
		return code.takeIf { this.format == format }.orEmpty()
	}
}