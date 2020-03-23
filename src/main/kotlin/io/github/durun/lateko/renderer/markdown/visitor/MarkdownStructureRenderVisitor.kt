package io.github.durun.lateko.renderer.markdown.visitor

import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.structure.*
import io.github.durun.lateko.renderer.common.StructureRenderVisitor

internal class MarkdownStructureRenderVisitor : StructureRenderVisitor {
	override val StructureElement.rendered: String get() = this.renderedAs(EmbeddedCode.Format.Markdown)
	private val sectionVisitor = MarkdownSectionsRenderVisitor(this)

	override fun visit(structure: Structure): String = structure.rendered
	override fun visit(paragraph: Paragraph): String = paragraph.rendered
	override fun visit(structure: StructureExtension): String = structure.rendered

	override fun visit(section: Section): String = sectionVisitor.visit(section)
	override fun visit(chapter: Chapter): String = sectionVisitor.visit(chapter)
	override fun visit(document: Document): String = sectionVisitor.visit(document)
}