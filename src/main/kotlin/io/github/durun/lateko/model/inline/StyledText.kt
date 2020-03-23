package io.github.durun.lateko.model.inline

data class StyledText(val text: Text, val styles: Set<Style>) : InlineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
	val string: String = text.text

	enum class Style {
		Strong,
		Italic,
		Typewriter,
		SmallCaps
	}

	override fun renderedAs(format: EmbeddedCode.Format): String? = when (format) {
		EmbeddedCode.Format.Markdown -> TODO()
		EmbeddedCode.Format.Tex -> TODO()
		else -> null
	}
}