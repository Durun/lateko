package lateko.dsl.structure

import lateko.command.tex.SimpleTexCommand
import lateko.dsl.Builder
import lateko.model.inline.EmbeddedCode
import lateko.model.line.Line.Companion.toLine
import lateko.model.structure.*
import lateko.model.structure.Structure.Companion.toStructure
import lateko.model.structure.StructureComposition.Companion.toComposition

abstract class StructureScope : Builder<StructureElement>() {
	companion object {
		protected fun StructureComposition.sortNest(): StructureComposition {
			return children.sortedBy { it is Section || it is Chapter }.toComposition()
		}
	}

	fun SimpleTexCommand.toLine(): Structure = EmbeddedCode(this.toString(), format = EmbeddedCode.Format.Tex).toLine().toStructure()


	override fun build(): StructureComposition = elements.toComposition()
}
