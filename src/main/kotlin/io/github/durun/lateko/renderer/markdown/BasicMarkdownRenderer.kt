package io.github.durun.lateko.renderer.markdown

import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.renderer.common.ChangeSectionIdVisitor
import io.github.durun.lateko.renderer.common.DocumentRenderVisitor
import io.github.durun.lateko.renderer.common.InlineRenderVisitor
import io.github.durun.lateko.renderer.common.StructureRenderVisitor
import io.github.durun.lateko.renderer.markdown.visitor.MarkdownInlineRenderVisitor
import io.github.durun.lateko.renderer.markdown.visitor.MarkdownLineRenderVisitor
import io.github.durun.lateko.renderer.markdown.visitor.MarkdownStructureRenderVisitor

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