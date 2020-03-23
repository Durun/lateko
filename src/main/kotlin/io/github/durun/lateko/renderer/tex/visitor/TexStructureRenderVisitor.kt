package io.github.durun.lateko.renderer.tex.visitor

import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.structure.Chapter
import io.github.durun.lateko.model.structure.Section
import io.github.durun.lateko.model.structure.Structure
import io.github.durun.lateko.model.structure.StructureExtension
import io.github.durun.lateko.renderer.common.StructureRenderVisitor

internal class TexStructureRenderVisitor : StructureRenderVisitor {
	private val sectionVisitor = TexSectionsRenderVisitor(this)

	override fun visit(structure: Structure): String = structure.element.renderedAs(EmbeddedCode.Format.Tex)
	override fun visit(structure: StructureExtension): String = structure.renderedAs(EmbeddedCode.Format.Tex)

	override fun visit(section: Section): String = sectionVisitor.visit(section)
	override fun visit(chapter: Chapter): String = sectionVisitor.visit(chapter)
	override fun visit(document: Document): String = sectionVisitor.visit(document)
}
