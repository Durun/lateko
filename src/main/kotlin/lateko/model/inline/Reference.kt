package lateko.model.inline

import lateko.model.structure.StructureElement

class Reference(val element: StructureElement) : InlineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
	val id: String get() = element.id
}