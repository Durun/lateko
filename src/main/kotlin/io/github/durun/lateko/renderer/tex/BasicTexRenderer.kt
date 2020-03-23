package io.github.durun.lateko.renderer.tex

import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.renderer.common.ChangeSectionIdVisitor
import io.github.durun.lateko.renderer.common.DocumentRenderVisitor
import io.github.durun.lateko.renderer.common.StructureRenderVisitor
import io.github.durun.lateko.renderer.tex.visitor.TexStructureRenderVisitor

object BasicTexRenderer : TexRenderer {
	override fun render(document: Document): String {
		return document
				.accept(ChangeSectionIdVisitor())
				.accept(TexRenderVisitor())
	}

	private class TexRenderVisitor : DocumentRenderVisitor,
			StructureRenderVisitor by TexStructureRenderVisitor() {

		override val InlineElement.rendered: String
			get() = this.renderedAs(outputFormat())

		override fun outputFormat() = EmbeddedCode.Format.Tex
	}
}