package io.github.durun.lateko.model.inline

import io.github.durun.lateko.model.Format

interface InlineExtension : InlineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)

	override fun renderedAs(format: Format): String {
		return when (format) {
			Format.Tex -> renderTex()
			Format.Markdown -> renderMarkdown()
			else -> renderPlane()
		}
	}

	fun renderTex(): String
	fun renderMarkdown(): String
	fun renderPlane():String
}