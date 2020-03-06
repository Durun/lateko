package lateko.dsl.structure

import lateko.command.TexCommand
import lateko.dsl.Builder
import lateko.dsl.LineScope
import lateko.dsl.LineScope.Companion.build
import lateko.dsl.inline.text
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

	fun section(name: InlineElement? = null, content: StructureScope.() -> Unit): Section = Section(name = name, content = content.build()).adding()

	fun section(name: String, content: StructureScope.() -> Unit) = section(name.text, content)

	fun chapter(name: InlineElement, content: StructureScope.() -> Unit): Chapter = Chapter(name = name, content = content.build()).adding()

	fun chapter(name: String, content: StructureScope.() -> Unit) = chapter(name.text, content)

	fun p(content: LineScope.() -> Unit): Paragraph = Paragraph(content.build()).adding()

	override fun build(): StructureComposition = StructureComposition(elements)
}
