package lateko.renderer.markdown

import lateko.model.Document
import lateko.model.inline.EmbeddedCode
import lateko.renderer.common.ChangeSectionIdVisitor
import lateko.renderer.common.DocumentRenderVisitor
import lateko.renderer.common.InlineRenderVisitor
import lateko.renderer.common.StructureRenderVisitor
import lateko.renderer.markdown.visitor.MarkdownInlineRenderVisitor
import lateko.renderer.markdown.visitor.MarkdownLineRenderVisitor
import lateko.renderer.markdown.visitor.MarkdownStructureRenderVisitor

object BasicMarkdownRenderer : MarkdownRenderer {
	override fun render(document: Document): String {
		return document
				.accept(ChangeSectionIdVisitor())
				.accept(MarkdownRenderVisitor())
	}

	private class MarkdownRenderVisitor : DocumentRenderVisitor,
			MarkdownInlineRenderVisitor,
			MarkdownLineRenderVisitor,
			StructureRenderVisitor by MarkdownStructureRenderVisitor(MarkdownInlineRenderVisitorObj, MarkdownLineRenderVisitorObj) {

		private object MarkdownInlineRenderVisitorObj : MarkdownInlineRenderVisitor {
			override fun EmbeddedCode.isEnabled(): Boolean = isMarkdown()
		}

		private object MarkdownLineRenderVisitorObj : MarkdownLineRenderVisitor {
			override val inlineRenderVisitor: InlineRenderVisitor = MarkdownInlineRenderVisitorObj
			override fun EmbeddedCode.isEnabled(): Boolean = isMarkdown()
		}

		override val inlineRenderVisitor: InlineRenderVisitor = MarkdownInlineRenderVisitorObj
		override fun EmbeddedCode.isEnabled(): Boolean = isMarkdown()
	}
}

private fun EmbeddedCode.isMarkdown() = this.format == EmbeddedCode.Format.Markdown