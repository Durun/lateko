package lateko.element

import lateko.dsl.InlineScope
import lateko.visitor.StructureVisitor


data class Paragraph(val content: InlineElement) : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)

	companion object {
		fun of(content: InlineScope.() -> Unit): Paragraph {
			val composition = InlineComposition.of(content)
			return Paragraph(composition)
		}
	}
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
