package lateko.renderer.markdown

import lateko.element.*
import lateko.visitor.DocumentRenderVisitor
import lateko.visitor.InlineRenderVisitor

internal class MarkdownRenderVisitor : DocumentRenderVisitor
		, InlineRenderVisitor by MarkdownInlineRenderVisitor {
	private var sectionNestLevel = 1

	override fun visit(paragraph: Paragraph): String {
		return paragraph.content.rendered + "\n"
	}

	override fun visit(document: Document): String {
		val title = document.name?.let { "# ${it.trim()}\n" }.orEmpty()
		return title + document.content.rendered
	}

	override fun visit(section: Section): String {
		sectionNestLevel++
		val sectionName = section.name?.rendered ?: "　"
		val content = "#".repeat(sectionNestLevel) + " " + sectionName.trim() + "\n" +
				section.content.rendered
		sectionNestLevel--
		return content
	}

	override fun visit(chapter: Chapter): String {
		sectionNestLevel++
		val content = "#".repeat(sectionNestLevel) + " " + chapter.name.rendered.trim() + "\n" +
				chapter.content.rendered
		sectionNestLevel--
		return content
	}

	override fun visit(structure: Structure): String = structure.element.rendered
}