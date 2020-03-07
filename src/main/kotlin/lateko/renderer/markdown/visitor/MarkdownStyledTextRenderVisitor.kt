package lateko.renderer.markdown.visitor

import lateko.model.inline.StyledText
import lateko.renderer.common.InlineRenderVisitor

internal interface MarkdownStyledTextRenderVisitor : InlineRenderVisitor {
	override fun visit(text: StyledText): String = TODO()
}