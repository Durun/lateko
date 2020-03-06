package lateko.element

import lateko.dsl.inline.LineScope
import lateko.dsl.structure.StructureScope
import lateko.visitor.InlineVisitor
import lateko.visitor.StructureVisitor

interface Composition : Element

data class InlineComposition(val children: List<InlineElement>) : Composition, InlineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)

	companion object {
		fun of(vararg elements: InlineElement): InlineComposition {
			return InlineComposition(elements.asList())
		}
	}
}

interface Line : LineElement {
	val element: InlineElement
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)

	companion object {
		fun InlineElement.toLine(): Line = LineData(this)
	}
}

private data class LineData(override val element: InlineElement) : Line

data class LineComposition(val children: List<LineElement>) : Composition, LineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)

	companion object {
		val empty: LineComposition = LineComposition(mutableListOf())
		fun of(content: LineScope.() -> Unit): LineComposition {
			val builder = LineScope()
			builder.content()
			return builder.build()
		}
	}
}

data class Structure(val element: LineElement) : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
}

data class StructureComposition(val children: List<StructureElement>) : Composition, StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)

	companion object {
		fun of(content: StructureScope.() -> Unit): StructureComposition {
			val builder = StructureScope()
			builder.content()
			return builder.build()
		}
	}
}
