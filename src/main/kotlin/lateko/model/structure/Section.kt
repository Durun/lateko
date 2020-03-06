package lateko.model.structure

import lateko.model.inline.InlineElement
import lateko.visitor.StructureVisitor

data class Section(
		val content: StructureElement,
		val name: InlineElement? = null) : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
}
