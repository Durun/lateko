package lateko.model.structure

import lateko.model.line.LineElement

data class Paragraph(val content: LineElement) : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
}