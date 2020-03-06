package lateko.element

import lateko.visitor.StructureVisitor


data class Paragraph(val content: LineElement) : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
}

data class Section(
		val content: StructureElement,
		val name: InlineElement? = null) : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
}

data class Chapter(
		val content: StructureElement,
		val name: InlineElement) : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
}
