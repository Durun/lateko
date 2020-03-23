package io.github.durun.lateko.model.inline

import io.github.durun.lateko.model.Format

data class EmbeddedCode(val code: String, val format: Format) : InlineExtension {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
	override fun renderedAs(format: Format): String {
		return code.takeIf { this.format == format }.orEmpty()
	}
}