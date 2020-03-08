package lateko.renderer.markdown

import lateko.model.Document
import lateko.renderer.common.DocumentRenderVisitor
import lateko.renderer.common.NameSectionIdVisitor
import lateko.renderer.common.StructureRenderVisitor
import lateko.renderer.markdown.visitor.MarkdownInlineRenderVisitor
import lateko.renderer.markdown.visitor.MarkdownStructureRenderVisitor

object BasicMarkdownRenderer : MarkdownRenderer {
	override fun render(document: Document): String {
		return document
				.accept(NameSectionIdVisitor())
				.accept(MarkdownRenderVisitor())
	}

	private class MarkdownRenderVisitor : DocumentRenderVisitor,
			MarkdownInlineRenderVisitor,
			StructureRenderVisitor by MarkdownStructureRenderVisitor(MarkdownInlineRenderVisitorObj) {
		private object MarkdownInlineRenderVisitorObj : MarkdownInlineRenderVisitor
	}
}