package lateko.element

import lateko.dsl.InlineScope
import lateko.dsl.LineScope
import lateko.dsl.StructureScope
import lateko.visitor.InlineVisitor
import lateko.visitor.StructureVisitor

interface Composition : Element

data class InlineComposition(val children: List<InlineElement>) : Composition, InlineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)

	companion object {
		fun of(content: InlineScope.() -> Unit): InlineComposition {
			val builder = InlineScope()
			builder.content()
			return builder.build()
		}
	}
}

data class Line(val element: InlineElement) : LineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
}

data class LineComposition(val children: MutableList<LineElement>) : Composition, LineElement { // TODO: MutableList -> List
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)

	companion object {
		fun of(content: LineScope.() -> Unit): LineComposition {
			val builder = LineScope()
			builder.content()
			return builder.build()
		}
	}
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
