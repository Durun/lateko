package lateko.renderer.markdown.visitor

import lateko.renderer.common.InlineRenderVisitor
import lateko.renderer.markdown.MarkdownEscaper

internal interface MarkdownInlineRenderVisitor : MarkdownInlineRenderVisitorCore,
		MarkdownRefRenderVisitor,
		MarkdownUrlRenderVisitor,
		MarkdownStyledTextRenderVisitor

internal interface MarkdownInlineRenderVisitorCore : InlineRenderVisitor {
	override fun String.escape(): String = MarkdownEscaper.escape(this)
}