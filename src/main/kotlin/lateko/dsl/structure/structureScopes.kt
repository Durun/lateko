package lateko.dsl.structure

import lateko.command.tex.SimpleTexCommand
import lateko.dsl.Builder
import lateko.model.inline.EmbeddedCode
import lateko.model.line.Line.Companion.toLine
import lateko.model.structure.*
import lateko.model.structure.Structure.Companion.toStructure
import lateko.model.structure.StructureComposition.Companion.toComposition


abstract class StructureScope : Builder<StructureElement>() {
	fun SimpleTexCommand.toLine(): Structure = EmbeddedCode(this.toString(), format = EmbeddedCode.Format.Tex).toLine().toStructure()

	override fun build(): StructureComposition = elements.toComposition()
}

class DocumentScope : StructureScope()

class ChapterScope : StructureScope() {
	companion object {
		fun (ChapterScope.() -> Unit).build(): StructureComposition {
			val builder = ChapterScope()
			builder.this()
			return builder.build().sortNest()
		}
	}
}

class SectionScope : StructureScope() {
	companion object {
		fun (SectionScope.() -> Unit).build(): StructureComposition {
			val builder = SectionScope()
			builder.this()
			return builder.build().sortNest()
		}
	}
}

private fun StructureComposition.sortNest(): StructureComposition {
	return children.sortedBy { it is Section || it is Chapter }.toComposition()
}