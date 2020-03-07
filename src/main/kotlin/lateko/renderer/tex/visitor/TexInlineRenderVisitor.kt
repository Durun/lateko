package lateko.renderer.tex.visitor

import lateko.model.inline.EmbeddedCode
import lateko.renderer.common.InlineRenderVisitor
import lateko.renderer.tex.TexEscaper

internal interface TexInlineRenderVisitor :
		TexCoreInlineRenderVisitor,
		TexUrlRenderVisitor,
		TexStyledTextRenderVisitor

internal interface TexCoreInlineRenderVisitor : InlineRenderVisitor {
	override fun String.escape(): String = TexEscaper.escape(this)
	override fun EmbeddedCode.isEnabled(): Boolean = this.format == EmbeddedCode.Format.Tex
}