package io.github.durun.lateko.renderer.tex.visitor

import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.renderer.common.InlineRenderVisitor
import io.github.durun.lateko.renderer.tex.TexEscaper

internal interface TexInlineRenderVisitor :
		TexCoreInlineRenderVisitor,
		TexRefRenderVisitor,
		TexUrlRenderVisitor,
		TexStyledTextRenderVisitor

internal interface TexCoreInlineRenderVisitor : InlineRenderVisitor {
	override fun String.escape(): String = TexEscaper.escape(this)
	override fun EmbeddedCode.isEnabled(): Boolean = this.format == EmbeddedCode.Format.Tex
}