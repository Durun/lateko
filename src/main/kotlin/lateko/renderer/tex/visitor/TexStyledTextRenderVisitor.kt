package lateko.renderer.tex.visitor

import lateko.model.inline.StyledText
import lateko.renderer.common.InlineRenderVisitor

internal interface TexStyledTextRenderVisitor : InlineRenderVisitor {
	override fun visit(text: StyledText): String = TODO()
}