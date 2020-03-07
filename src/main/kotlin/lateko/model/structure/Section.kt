package lateko.model.structure

import lateko.model.inline.InlineElement

data class Section(
		val content: StructureElement,
		val name: InlineElement? = null) : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
}
