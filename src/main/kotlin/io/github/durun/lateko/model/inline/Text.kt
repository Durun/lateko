package io.github.durun.lateko.model.inline

data class Text(val text: String) : InlineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)

	override fun renderedAs(format: EmbeddedCode.Format): String? = when (format) {
		EmbeddedCode.Format.Markdown -> TODO()
		EmbeddedCode.Format.Tex -> TODO()
		else -> null
	}
}