package lateko.model

import lateko.visitor.StructureVisitor

interface Structure : StructureElement {
	val element: LineElement
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)

	companion object {
		fun LineElement.toStructure(): Structure = StructureData(this)
	}

	private data class StructureData(override val element: LineElement) : Structure
}
