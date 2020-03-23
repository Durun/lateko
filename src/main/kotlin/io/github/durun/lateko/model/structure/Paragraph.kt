package io.github.durun.lateko.model.structure

import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.line.LineElement

data class Paragraph(val content: LineElement) : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
	override fun renderedAs(format: EmbeddedCode.Format): String = when (format) {
		EmbeddedCode.Format.Markdown -> TODO()
		EmbeddedCode.Format.Tex -> TODO()
		else -> TODO()
	}
}