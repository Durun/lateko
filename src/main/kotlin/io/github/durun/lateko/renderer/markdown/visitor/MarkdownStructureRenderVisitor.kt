package io.github.durun.lateko.renderer.markdown.visitor

import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.structure.Chapter
import io.github.durun.lateko.model.structure.Section
import io.github.durun.lateko.model.structure.Structure
import io.github.durun.lateko.model.structure.StructureExtension
import io.github.durun.lateko.renderer.common.StructureRenderVisitor

internal class MarkdownStructureRenderVisitor : StructureRenderVisitor {
	private val sectionVisitor = MarkdownSectionsRenderVisitor(this)

	override fun visit(structure: Structure): String = structure.element.renderedAs(EmbeddedCode.Format.Markdown)
	override fun visit(structure: StructureExtension): String = structure.renderedAs(EmbeddedCode.Format.Markdown)

	override fun visit(section: Section): String = sectionVisitor.visit(section)
	override fun visit(chapter: Chapter): String = sectionVisitor.visit(chapter)
	override fun visit(document: Document): String = sectionVisitor.visit(document)
}