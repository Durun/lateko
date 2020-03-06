package lateko.dsl.structure

import lateko.command.TexCommand
import lateko.dsl.Builder
import lateko.element.*

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

	fun TexCommand.toLine(): Structure = Structure(Line(EmbeddedCode(this.toString(), format = EmbeddedCode.Format.Tex)))


	override fun build(): StructureComposition = StructureComposition(elements)
}
