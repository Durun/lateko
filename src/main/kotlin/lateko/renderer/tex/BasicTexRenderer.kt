package lateko.renderer.tex

import lateko.model.Document
import lateko.renderer.common.DocumentRenderVisitor
import lateko.renderer.common.NameSectionIdVisitor
import lateko.renderer.common.StructureRenderVisitor
import lateko.renderer.tex.visitor.TexInlineRenderVisitor
import lateko.renderer.tex.visitor.TexStructureRenderVisitor

object BasicTexRenderer : TexRenderer {
	override fun render(document: Document): String {
		return document
				.accept(NameSectionIdVisitor())
				.accept(TexRenderVisitor())
	}

	private class TexRenderVisitor : DocumentRenderVisitor,
			TexInlineRenderVisitor,
			StructureRenderVisitor by TexStructureRenderVisitor(TexInlineRenderVisitorObj) {
		private object TexInlineRenderVisitorObj : TexInlineRenderVisitor
	}
}