package io.github.durun.lateko.model

import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.line.LineElement
import io.github.durun.lateko.model.structure.StructureElement
import io.github.durun.lateko.model.structure.StructureVisitor

data class Document(val name: String? = null, val header: LineElement, val content: StructureElement) : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
	fun <R> accept(visitor: DocumentVisitor<R>): R = visitor.visit(this)
	override fun renderedAs(format: EmbeddedCode.Format): String = when (format) {
		EmbeddedCode.Format.Markdown -> TODO()
		EmbeddedCode.Format.Tex -> TODO()
		else -> TODO()
	}
}