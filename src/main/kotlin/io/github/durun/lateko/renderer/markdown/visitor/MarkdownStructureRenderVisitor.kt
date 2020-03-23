package io.github.durun.lateko.renderer.markdown.visitor

import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.line.LineElement
import io.github.durun.lateko.model.structure.Chapter
import io.github.durun.lateko.model.structure.Paragraph
import io.github.durun.lateko.model.structure.Section
import io.github.durun.lateko.model.structure.Structure
import io.github.durun.lateko.renderer.common.InlineRenderVisitor
import io.github.durun.lateko.renderer.common.StructureRenderVisitor

internal class MarkdownStructureRenderVisitor(
		inlineVisitor: InlineRenderVisitor,
		lineVisitor: MarkdownLineRenderVisitor) : StructureRenderVisitor {
	private val coreVisitor = MarkdownCoreLineRenderVisitor(lineVisitor)
	private val sectionVisitor = MarkdownSectionsRenderVisitor(inlineVisitor, lineVisitor, this)

	override fun visit(structure: Structure): String = coreVisitor.visit(structure)
	override fun visit(paragraph: Paragraph): String = coreVisitor.visit(paragraph)

	override fun visit(section: Section): String = sectionVisitor.visit(section)
	override fun visit(chapter: Chapter): String = sectionVisitor.visit(chapter)
	override fun visit(document: Document): String = sectionVisitor.visit(document)

	private class MarkdownCoreLineRenderVisitor(
			private val lineVisitor: MarkdownLineRenderVisitor) {
		private val LineElement.rendered: String
			get() = this.accept(lineVisitor)

		fun visit(structure: Structure): String = structure.element.rendered
		fun visit(paragraph: Paragraph): String = paragraph.content.rendered + "\n"
	}
}