package lateko.model.line

import lateko.model.inline.InlineElement
import lateko.visitor.InlineVisitor

interface Line : LineElement {
	val element: InlineElement
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)

	companion object {
		fun InlineElement.toLine(): Line = LineData(this)
	}

	private data class LineData(override val element: InlineElement) : Line
}

