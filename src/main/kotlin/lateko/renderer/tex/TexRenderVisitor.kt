package lateko.renderer.tex

import lateko.command.Begin
import lateko.command.End
import lateko.dsl.structure.IllegalNestError
import lateko.model.*
import lateko.visitor.DocumentRenderVisitor
import lateko.visitor.InlineRenderVisitor
import org.jetbrains.kotlin.org.jdom.IllegalNameException

internal class TexRenderVisitor : DocumentRenderVisitor
		, InlineRenderVisitor by TexInlineRenderVisitor {
	private var sectionNestLevel = 0
	private var chapterNestLevel = 0

	override fun visit(paragraph: Paragraph): String = paragraph.content.rendered + "\n"

	override fun visit(section: Section): String {
		assert(sectionNestLevel >= 0)
		sectionNestLevel++
		val sectionName = section.name?.rendered.orEmpty()
		val sectionCommand = when (sectionNestLevel) {
			1 -> lateko.command.Section(sectionName)
			2 -> lateko.command.SubSection(sectionName)
			3 -> lateko.command.SubSubSection(sectionName)
			else -> throw IllegalNestError("Too many section nest.")
		}
		val content = "$sectionCommand\n${section.content.rendered}"
		sectionNestLevel--
		return content
	}

	override fun visit(chapter: Chapter): String {
		assert(chapterNestLevel >= 0)
		chapterNestLevel++
		if (sectionNestLevel != 0) throw IllegalNestError("Chapter must not be in a section.")
		if (chapterNestLevel > 1) throw IllegalNestError("Too many chapter nest.")

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