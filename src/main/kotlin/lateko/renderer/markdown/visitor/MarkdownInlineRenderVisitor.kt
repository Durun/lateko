package lateko.renderer.markdown.visitor

import lateko.model.inline.EmbeddedCode
import lateko.renderer.common.InlineRenderVisitor
import lateko.renderer.markdown.MarkdownEscaper

internal interface MarkdownInlineRenderVisitor : MarkdownInlineRenderVisitorCore,
		MarkdownUrlRenderVisitor,
		MarkdownStyledTextRenderVisitor

internal interface MarkdownInlineRenderVisitorCore : InlineRenderVisitor {
	override fun String.escape(): String = MarkdownEscaper.escape(this)
	override fun EmbeddedCode.isEnabled(): Boolean = this.format == EmbeddedCode.Format.Markdown
}