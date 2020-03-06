package lateko.renderer.markdown

import lateko.model.Document
import lateko.model.structure.Chapter
import lateko.model.structure.Paragraph
import lateko.model.structure.Section
import lateko.model.structure.Structure
import lateko.renderer.common.DocumentRenderVisitor
import lateko.renderer.common.InlineRenderVisitor

internal class MarkdownRenderVisitor : DocumentRenderVisitor
		, InlineRenderVisitor by MarkdownInlineRenderVisitor {
	private var sectionNestLevel = 1

	override fun visit(paragraph: Paragraph): String = paragraph.content.rendered + "\n"

	override fun visit(document: Document): String {
		val title = document.name?.let { "# ${it.trim()}\n" }.orEmpty()
		return title + document.content.rendered
	}

	override fun visit(section: Section): String {
		assert(sectionNestLevel >= 1)
		sectionNestLevel++
		val sectionName = section.name?.rendered ?: "　"
		val content = "#".repeat(sectionNestLevel) + " " + sectionName.trim() + "\n" +
				section.content.rendered
		sectionNestLevel--
		return content
	}

	override fun visit(chapter: Chapter): String {
		assert(sectionNestLevel >= 1)
		sectionNestLevel++
		val content = "#".repeat(sectionNestLevel) + " " + chapter.name.rendered.trim() + "\n" +
				chapter.content.rendered
		sectionNestLevel--
		return content
	}

	override fun visit(structure: Structure): String = structure.element.rendered
}