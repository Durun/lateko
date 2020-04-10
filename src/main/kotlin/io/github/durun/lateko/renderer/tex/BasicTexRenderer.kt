package io.github.durun.lateko.renderer.tex

import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.structure.StructureElement
import io.github.durun.lateko.renderer.common.ChangeSectionIdVisitor
import io.github.durun.lateko.renderer.common.DocumentRenderVisitor
import io.github.durun.lateko.renderer.common.StructureRenderVisitor
import io.github.durun.lateko.renderer.tex.visitor.TexStructureRenderVisitor

object BasicTexRenderer : TexRenderer {
	override fun render(document: StructureElement): String {
		return document
				.accept(ChangeSectionIdVisitor(document.context))
				.accept(TexRenderVisitor())
	}

	private class TexRenderVisitor : DocumentRenderVisitor,
			StructureRenderVisitor by TexStructureRenderVisitor() {
		override val outputFormat = Format.Tex
		override fun InlineElement.rendered(): String = this.renderedAs(outputFormat)
	}
}