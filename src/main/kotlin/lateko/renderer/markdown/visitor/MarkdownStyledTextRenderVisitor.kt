package lateko.renderer.markdown.visitor

import lateko.command.markdown.BoldItalicText
import lateko.command.markdown.BoldText
import lateko.command.markdown.CodeText
import lateko.command.markdown.ItalicText
import lateko.model.inline.StyledText
import lateko.renderer.common.InlineRenderVisitor

internal interface MarkdownStyledTextRenderVisitor : InlineRenderVisitor {
	override fun visit(text: StyledText): String {
		return text.styles.let {
			when (true) {
				it.containsAll(setOf(StyledText.Style.Strong, StyledText.Style.Italic)) -> BoldItalicText(text.string)
				it.contains(StyledText.Style.Strong) -> BoldText(text.string)
				it.contains(StyledText.Style.Italic) -> ItalicText(text.string)
				it.contains(StyledText.Style.Typewriter) -> CodeText(text.string)
				else -> null
			}?.toString() ?: text.string
		}
	}
}