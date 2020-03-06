package lateko.dsl.structure

import lateko.command.TexCommand
import lateko.dsl.Builder
import lateko.element.*
import lateko.element.Line.Companion.toLine

open class StructureScope : Builder<StructureElement, StructureComposition>() {
	companion object {
		private fun StructureComposition.sortNest(): StructureComposition {
			return this.copy(children = children.sortedBy { it is Section || it is Chapter })
		}

		fun (StructureScope.() -> Unit).build(): StructureComposition {
			val builder = StructureScope()
			builder.this()
			return builder.build().sortNest()
		}
	}

	fun TexCommand.toLine(): Structure = Structure(EmbeddedCode(this.toString(), format = EmbeddedCode.Format.Tex).toLine())


	override fun build(): StructureComposition = StructureComposition(elements)
}
