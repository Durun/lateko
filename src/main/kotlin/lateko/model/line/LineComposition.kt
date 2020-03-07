package lateko.model.line

import lateko.model.Composition
import lateko.model.inline.InlineVisitor

interface LineComposition : Composition<LineElement>, LineElement {
	val children: List<LineElement>
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)

	companion object {
		fun Iterable<LineElement>.toComposition(): LineComposition {
			return LineCompositionData(this.toList())
		}
	}

	private data class LineCompositionData(override val children: List<LineElement>) : LineComposition
}