package io.github.durun.lateko.model.inline

import io.github.durun.lateko.model.Composition

interface InlineComposition : Composition<InlineElement>, InlineElement {
	val children: List<InlineElement>
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
	override fun renderedAs(format: EmbeddedCode.Format): String? = children.joinToString("") { it.renderedAs(format).orEmpty() }.takeIf { it.isNotEmpty() }

	companion object {
		fun Iterable<InlineElement>.toComposition(): InlineComposition {
			return InlineCompositionData(this.toList())
		}
	}

	private data class InlineCompositionData(override val children: List<InlineElement>) : InlineComposition
}
