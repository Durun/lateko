package lateko.model.inline

import lateko.model.Composition
import lateko.visitor.InlineVisitor

interface InlineComposition : Composition<InlineElement>, InlineElement {
	val children: List<InlineElement>
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)

	companion object {
		fun Iterable<InlineElement>.toComposition(): InlineComposition {
			return InlineCompositionData(this.toList())
		}
	}

	private data class InlineCompositionData(override val children: List<InlineElement>) : InlineComposition
}
