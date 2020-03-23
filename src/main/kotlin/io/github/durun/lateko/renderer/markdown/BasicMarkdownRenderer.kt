package io.github.durun.lateko.renderer.markdown

import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.renderer.common.ChangeSectionIdVisitor
import io.github.durun.lateko.renderer.common.DocumentRenderVisitor
import io.github.durun.lateko.renderer.common.InlineRenderVisitor
import io.github.durun.lateko.renderer.common.StructureRenderVisitor
import io.github.durun.lateko.renderer.markdown.visitor.MarkdownLineRenderVisitor
import io.github.durun.lateko.renderer.markdown.visitor.MarkdownStructureRenderVisitor

object BasicMarkdownRenderer : MarkdownRenderer {
	override fun render(document: Document): String {
		return document
				.accept(ChangeSectionIdVisitor())
				.accept(MarkdownRenderVisitor())
	}

	private class MarkdownRenderVisitor : DocumentRenderVisitor,
			MarkdownLineRenderVisitor,
			StructureRenderVisitor by MarkdownStructureRenderVisitor(MarkdownInlineRenderVisitorObj, MarkdownLineRenderVisitorObj) {

		private object MarkdownInlineRenderVisitorObj : InlineRenderVisitor {
			override fun outputFormat() = EmbeddedCode.Format.Markdown
		}

		private object MarkdownLineRenderVisitorObj : MarkdownLineRenderVisitor {
			override val inlineRenderVisitor: InlineRenderVisitor = MarkdownInlineRenderVisitorObj
			override fun outputFormat() = EmbeddedCode.Format.Markdown
		}

		override val inlineRenderVisitor: InlineRenderVisitor = MarkdownInlineRenderVisitorObj
		override fun outputFormat() = EmbeddedCode.Format.Markdown
	}
}