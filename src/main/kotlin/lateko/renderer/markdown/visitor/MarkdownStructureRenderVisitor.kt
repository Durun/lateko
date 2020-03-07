package lateko.renderer.markdown.visitor

import lateko.model.Document
import lateko.model.line.LineElement
import lateko.model.structure.Chapter
import lateko.model.structure.Paragraph
import lateko.model.structure.Section
import lateko.model.structure.Structure
import lateko.renderer.common.StructureRenderVisitor

internal class MarkdownStructureRenderVisitor(V: MarkdownInlineRenderVisitor) : StructureRenderVisitor {
	private val coreVisitor = MarkdownCoreInlineRenderVisitor(V)
	private val sectionVisitor = MarkdownSectionsRenderVisitor(V, this)

	override fun visit(structure: Structure): String = coreVisitor.visit(structure)
	override fun visit(paragraph: Paragraph): String = coreVisitor.visit(paragraph)

	override fun visit(section: Section): String = sectionVisitor.visit(section)
	override fun visit(chapter: Chapter): String = sectionVisitor.visit(chapter)
	override fun visit(document: Document): String = sectionVisitor.visit(document)

	private class MarkdownCoreInlineRenderVisitor(
			private val V: MarkdownInlineRenderVisitor) {
		private val LineElement.rendered: String
			get() = this.accept(V)

		fun visit(structure: Structure): String = structure.element.rendered
		fun visit(paragraph: Paragraph): String = paragraph.content.rendered + "\n"
	}
}