package lateko.element

import lateko.visitor.InlineVisitor
import lateko.visitor.StructureVisitor

interface Composition<E : Element> : Element

interface InlineComposition : Composition<InlineElement>, InlineElement {
	val children: List<InlineElement>
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)

	companion object {
		fun Iterable<InlineElement>.toComposition(): InlineComposition {
			return InlineCompositionData(this.toList())
		}

		fun of(vararg elements: InlineElement): InlineComposition {
			return elements.asIterable().toComposition()
		}
	}
}

private data class InlineCompositionData(override val children: List<InlineElement>) : InlineComposition


interface Line : LineElement {
	val element: InlineElement
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)

	companion object {
		fun InlineElement.toLine(): Line = LineData(this)
	}
}

private data class LineData(override val element: InlineElement) : Line

interface LineComposition : Composition<LineElement>, LineElement {
	val children: List<LineElement>
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)

	companion object {
		fun Iterable<LineElement>.toComposition(): LineComposition {
			return LineCompositionData(this.toList())
		}
	}
}

private data class LineCompositionData(override val children: List<LineElement>) : LineComposition

interface Structure : StructureElement {
	val element: LineElement
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)

	companion object {
		fun LineElement.toStructure(): Structure = StructureData(this)
	}
}

private data class StructureData(override val element: LineElement) : Structure

interface StructureComposition : Composition<StructureElement>, StructureElement {
	val children: List<StructureElement>
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)

	companion object {
		fun Iterable<StructureElement>.toComposition(): StructureComposition {
			return StructureCompositionData(this.toList())
		}
	}
}

private data class StructureCompositionData(override val children: List<StructureElement>) : StructureComposition
