package lateko.renderer.tex.visitor

import lateko.model.Document
import lateko.model.line.LineElement
import lateko.model.structure.Chapter
import lateko.model.structure.Paragraph
import lateko.model.structure.Section
import lateko.model.structure.Structure
import lateko.renderer.common.StructureRenderVisitor

internal class TexStructureRenderVisitor(
		inlineVisitor: TexInlineRenderVisitor,
		lineVisitor: TexLineRenderVisitor) : StructureRenderVisitor {
	private val coreVisitor = TexCoreStructureRenderVisitor(lineVisitor)
	private val sectionVisitor = TexSectionsRenderVisitor(inlineVisitor, lineVisitor, this)

	override fun visit(structure: Structure): String = coreVisitor.visit(structure)
	override fun visit(paragraph: Paragraph): String = coreVisitor.visit(paragraph)

	override fun visit(section: Section): String = sectionVisitor.visit(section)
	override fun visit(chapter: Chapter): String = sectionVisitor.visit(chapter)
	override fun visit(document: Document): String = sectionVisitor.visit(document)

	private class TexCoreStructureRenderVisitor(
			private val lineVisitor: TexLineRenderVisitor) {
		private val LineElement.rendered: String
			get() = this.accept(lineVisitor)

		fun visit(structure: Structure): String = structure.element.rendered
		fun visit(paragraph: Paragraph): String = paragraph.content.rendered + "\n"
	}
}
