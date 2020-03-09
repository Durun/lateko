package lateko.dsl.structure

import lateko.command.tex.SimpleTexCommand
import lateko.dsl.Builder
import lateko.model.inline.EmbeddedCode
import lateko.model.line.Line.Companion.toLine
import lateko.model.structure.Structure
import lateko.model.structure.Structure.Companion.toStructure
import lateko.model.structure.StructureComposition
import lateko.model.structure.StructureComposition.Companion.toComposition
import lateko.model.structure.StructureElement

abstract class StructureScope : Builder<StructureElement>() {
	fun SimpleTexCommand.toLine(): Structure = EmbeddedCode(this.toString(), format = EmbeddedCode.Format.Tex).toLine().toStructure()

	override fun build(): StructureComposition = elements.toComposition()
}
