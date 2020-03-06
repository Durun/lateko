package lateko.model

import lateko.model.line.LineElement
import lateko.model.structure.StructureElement
import lateko.visitor.DocumentVisitor
import lateko.visitor.StructureVisitor

data class Document(val name: String? = null, val header: LineElement, val content: StructureElement) : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
	fun <R> accept(visitor: DocumentVisitor<R>): R = visitor.visit(this)
}