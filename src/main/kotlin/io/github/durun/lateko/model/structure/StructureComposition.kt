package io.github.durun.lateko.model.structure

import io.github.durun.lateko.model.Composition
import io.github.durun.lateko.model.inline.EmbeddedCode

interface StructureComposition : Composition<StructureElement>, StructureElement {
	val children: List<StructureElement>
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
	override fun renderedAs(format: EmbeddedCode.Format): String = when (format) {
		EmbeddedCode.Format.Markdown -> TODO()
		EmbeddedCode.Format.Tex -> TODO()
		else -> TODO()
	}

	companion object {
		fun Iterable<StructureElement>.toComposition(): StructureComposition {
			return StructureCompositionData(this.toList())
		}
	}

	private data class StructureCompositionData(override val children: List<StructureElement>) : StructureComposition
}
