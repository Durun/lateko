package io.github.durun.lateko.renderer.markdown.visitor

import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.line.LineElement
import io.github.durun.lateko.model.structure.Chapter
import io.github.durun.lateko.model.structure.Paragraph
import io.github.durun.lateko.model.structure.Section
import io.github.durun.lateko.model.structure.Structure
import io.github.durun.lateko.renderer.common.StructureRenderVisitor

internal class MarkdownStructureRenderVisitor : StructureRenderVisitor {
	private val coreVisitor = MarkdownCoreLineRenderVisitor()
	private val sectionVisitor = MarkdownSectionsRenderVisitor(this)

	override fun visit(structure: Structure): String = coreVisitor.visit(structure)
	override fun visit(paragraph: Paragraph): String = coreVisitor.visit(paragraph)

	override fun visit(section: Section): String = sectionVisitor.visit(section)
	override fun visit(chapter: Chapter): String = sectionVisitor.visit(chapter)
	override fun visit(document: Document): String = sectionVisitor.visit(document)

	private class MarkdownCoreLineRenderVisitor {
		private val LineElement.rendered: String
			get() = this.renderedAs(EmbeddedCode.Format.Markdown)

		fun visit(structure: Structure): String = structure.element.rendered
		fun visit(paragraph: Paragraph): String = paragraph.content.rendered + "\n"
	}
}