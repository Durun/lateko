package io.github.durun.lateko.model.line

import io.github.durun.lateko.model.Composition

interface LineComposition : Composition<LineElement>, LineElement {
	val children: List<LineElement>
	override fun <R> accept(visitor: LineVisitor<R>): R = visitor.visit(this)

	companion object {
		fun Iterable<LineElement>.toComposition(): LineComposition {
			return LineCompositionData(this.toList())
		}
	}

	private data class LineCompositionData(override val children: List<LineElement>) : LineComposition
}