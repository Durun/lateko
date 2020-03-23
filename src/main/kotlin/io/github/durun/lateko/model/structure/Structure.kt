package io.github.durun.lateko.model.structure

import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.line.LineElement

interface Structure : StructureElement {
	val element: LineElement
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
	override fun renderedAs(format: EmbeddedCode.Format): String = element.renderedAs(format)

	companion object {
		fun LineElement.toStructure(): Structure = StructureData(this)
	}

	private data class StructureData(override val element: LineElement) : Structure
}
