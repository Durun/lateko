package lateko.dsl.structure

import lateko.command.TexCommand
import lateko.dsl.Builder
import lateko.dsl.LineScope
import lateko.dsl.LineScope.Companion.build
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

	fun p(content: LineScope.() -> Unit): Paragraph = Paragraph(content.build()).adding()

	override fun build(): StructureComposition = StructureComposition(elements)
}
