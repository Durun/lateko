package lateko.renderer.tex

import lateko.command.Begin
import lateko.command.End
import lateko.element.*
import lateko.visitor.DocumentRenderVisitor
import lateko.visitor.InlineRenderVisitor

internal class TexRenderVisitor : DocumentRenderVisitor
		, InlineRenderVisitor by TexInlineRenderVisitor {
	private var sectionNestLevel = 0
	private var chapterNestLevel = 0

	private val InlineElement.rendered: String
		get() {
			return this.accept(TexInlineRenderVisitor)
		}
	private val LineElement.rendered: String
		get() {
			return this.accept(TexInlineRenderVisitor)
		}
	private val StructureElement.rendered: String
		get() {
			return this.accept(this@TexRenderVisitor)
		}

	override fun visit(composition: StructureComposition): String {
		return composition.children.joinToString("") { it.rendered }
	}

	override fun visit(paragraph: Paragraph): String = paragraph.content.rendered + "\n"

	override fun visit(section: Section): String {
		assert(sectionNestLevel >= 0)
		sectionNestLevel++
		val sectionName = section.name?.rendered.orEmpty()
		val sectionCommand = when (sectionNestLevel) {
			1 -> lateko.command.Section(sectionName)
			2 -> lateko.command.SubSection(sectionName)
			3 -> lateko.command.SubSubSection(sectionName)
			else -> throw Exception("Too many section nest level.") // TODO TooManyNestError
		}
		val content = "$sectionCommand\n${section.content.rendered}"
		sectionNestLevel--
		return content
	}

	override fun visit(chapter: Chapter): String {
		assert(chapterNestLevel >= 0)
		chapterNestLevel++
		if (sectionNestLevel != 0) throw Exception("Chapter must not be in a section.") // TODO
		if (chapterNestLevel > 1) throw Exception("Too many chapter nest level.") // TODO

		val chapterName = chapter.name.rendered
		val chapterCommand = lateko.command.Chapter(chapterName)
		val content = "$chapterCommand\n${chapter.content.rendered}"
		chapterNestLevel--
		return content
	}

	override fun visit(document: Document): String {
		return """${document.header.rendered}
			|${Begin("document")}
			|${document.content.rendered}
			|${End("document")}""".trimMargin()
	}

	override fun visit(structure: Structure): String = structure.element.rendered
}