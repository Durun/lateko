package io.github.durun.lateko.renderer.markdown.visitor

import io.github.durun.lateko.renderer.common.InlineRenderVisitor
import io.github.durun.lateko.renderer.markdown.MarkdownEscaper

internal interface MarkdownInlineRenderVisitor : MarkdownInlineRenderVisitorCore,
		MarkdownRefRenderVisitor,
		MarkdownStyledTextRenderVisitor

internal interface MarkdownInlineRenderVisitorCore : InlineRenderVisitor {
	override fun String.escape(): String = MarkdownEscaper.escape(this)
}