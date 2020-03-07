package lateko.renderer.markdown

import lateko.model.Document
import lateko.renderer.common.DocumentRenderVisitor
import lateko.renderer.common.StructureRenderVisitor
import lateko.renderer.markdown.visitor.MarkdownInlineRenderVisitor
import lateko.renderer.markdown.visitor.MarkdownStructureRenderVisitor

object BasicMarkdownRenderer : MarkdownRenderer {
	override fun render(document: Document): String {
		val visitor = MarkdownRenderVisitor()
		return document.accept(visitor)
	}

	private class MarkdownRenderVisitor : DocumentRenderVisitor,
			MarkdownInlineRenderVisitor,
			StructureRenderVisitor by MarkdownStructureRenderVisitor(MarkdownInlineRenderVisitorObj) {
		private object MarkdownInlineRenderVisitorObj : MarkdownInlineRenderVisitor
	}
}