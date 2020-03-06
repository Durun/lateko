package lateko.element

import lateko.visitor.StructureVisitor

interface StructureComposition : Composition<StructureElement>, StructureElement {
	val children: List<StructureElement>
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)

	companion object {
		fun Iterable<StructureElement>.toComposition(): StructureComposition {
			return StructureCompositionData(this.toList())
		}
	}

	private data class StructureCompositionData(override val children: List<StructureElement>) : StructureComposition
}
