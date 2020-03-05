package lateko.dsl

import lateko.command.TexCommand
import lateko.dsl.inline.text
import lateko.element.*

open class StructureScope : Builder<StructureElement, StructureComposition>() {
	val contents: List<StructureElement> get() = elements

	private fun StructureComposition.sortNest(): StructureComposition {
		return this.copy(children = children.sortedBy { it is Section || it is Chapter })
	}

	private fun TexCommand.toLine(): Structure = Structure(Line(EmbeddedCode(this.toString(), format = EmbeddedCode.Format.Tex)))

	protected fun <T : StructureElement> addStructure(content: StructureScope.() -> Unit, constructor: (StructureElement) -> T): T {
		val builder = StructureScope()
		builder.content()
		val element = constructor(builder.build().sortNest())
		elements += element
		return element
	}

	fun makeTitle(): Structure = TexCommand("maketitle").toLine().adding()

	fun section(name: InlineElement? = null, content: StructureScope.() -> Unit): Section = addStructure(content) { Section(name = name, content = it) }

	fun section(name: String, content: StructureScope.() -> Unit) = section(name.text, content)

	fun chapter(name: InlineElement, content: StructureScope.() -> Unit): Chapter = addStructure(content) { Chapter(name = name, content = it) }

	fun chapter(name: String, content: StructureScope.() -> Unit) = chapter(name.text, content)

	fun p(content: LineScope.() -> Unit): Paragraph {
		val paragraph = Paragraph.of(content)
		elements += paragraph
		return paragraph
	}

	override fun build(): StructureComposition = StructureComposition(elements)
}

