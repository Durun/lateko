package io.github.durun.lateko.dsl.structure

import io.github.durun.lateko.command.tex.SimpleTexCommand
import io.github.durun.lateko.dsl.Builder
import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.line.Line.Companion.toLine
import io.github.durun.lateko.model.structure.*
import io.github.durun.lateko.model.structure.Structure.Companion.toStructure
import io.github.durun.lateko.model.structure.StructureComposition.Companion.toComposition


abstract class StructureScope : Builder<StructureElement>() {
	fun SimpleTexCommand.toLine(): Structure = EmbeddedCode(this.toString(), format = Format.Tex).toLine().toStructure()

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