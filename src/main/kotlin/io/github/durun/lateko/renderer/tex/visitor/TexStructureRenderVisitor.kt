package io.github.durun.lateko.renderer.tex.visitor

import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.line.LineElement
import io.github.durun.lateko.model.structure.*
import io.github.durun.lateko.renderer.common.StructureRenderVisitor

internal class TexStructureRenderVisitor : StructureRenderVisitor {
	private val coreVisitor = TexCoreStructureRenderVisitor()
	private val sectionVisitor = TexSectionsRenderVisitor(this)

	override fun visit(structure: Structure): String = coreVisitor.visit(structure)
	override fun visit(paragraph: Paragraph): String = coreVisitor.visit(paragraph)
	override fun visit(structure: StructureExtension): String = TODO()

	override fun visit(section: Section): String = sectionVisitor.visit(section)
	override fun visit(chapter: Chapter): String = sectionVisitor.visit(chapter)
	override fun visit(document: Document): String = sectionVisitor.visit(document)

	private class TexCoreStructureRenderVisitor {
		private val LineElement.rendered: String
			get() = this.renderedAs(EmbeddedCode.Format.Tex)

		fun visit(structure: Structure): String = structure.element.rendered
		fun visit(paragraph: Paragraph): String = paragraph.content.rendered + "\n"

	}
}
