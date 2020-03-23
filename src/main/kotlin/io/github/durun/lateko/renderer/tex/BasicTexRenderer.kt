package io.github.durun.lateko.renderer.tex

import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.renderer.common.ChangeSectionIdVisitor
import io.github.durun.lateko.renderer.common.DocumentRenderVisitor
import io.github.durun.lateko.renderer.common.InlineRenderVisitor
import io.github.durun.lateko.renderer.common.StructureRenderVisitor
import io.github.durun.lateko.renderer.tex.visitor.TexInlineRenderVisitor
import io.github.durun.lateko.renderer.tex.visitor.TexLineRenderVisitor
import io.github.durun.lateko.renderer.tex.visitor.TexStructureRenderVisitor

object BasicTexRenderer : TexRenderer {
	override fun render(document: Document): String {
		return document
				.accept(ChangeSectionIdVisitor())
				.accept(TexRenderVisitor())
	}

	private class TexRenderVisitor : DocumentRenderVisitor,
			TexInlineRenderVisitor,
			TexLineRenderVisitor,
			StructureRenderVisitor by TexStructureRenderVisitor(TexInlineRenderVisitorObj, TexLineRenderVisitorObj) {

		private object TexInlineRenderVisitorObj : TexInlineRenderVisitor {
			override fun EmbeddedCode.isEnabled(): Boolean = isTex()
		}

		private object TexLineRenderVisitorObj : TexLineRenderVisitor {
			override val inlineRenderVisitor: InlineRenderVisitor = TexInlineRenderVisitorObj

			override fun EmbeddedCode.isEnabled(): Boolean = isTex()
		}

		override val inlineRenderVisitor: InlineRenderVisitor = TexInlineRenderVisitorObj
		override fun EmbeddedCode.isEnabled(): Boolean = isTex()
	}
}

private fun EmbeddedCode.isTex() = this.format == EmbeddedCode.Format.Tex