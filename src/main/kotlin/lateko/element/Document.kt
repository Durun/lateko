package lateko.element

import lateko.visitor.DocumentVisitor
import lateko.visitor.StructureVisitor

data class Document(val name: String? = null, val content: StructureElement) : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
	fun <R> accept(visitor: DocumentVisitor<R>): R = visitor.visit(this)
}