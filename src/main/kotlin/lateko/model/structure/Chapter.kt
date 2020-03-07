package lateko.model.structure

import lateko.model.inline.InlineElement

data class Chapter(
		val content: StructureElement,
		val name: InlineElement) : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
}
