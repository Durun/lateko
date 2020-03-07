package lateko.renderer.tex.visitor

import lateko.command.tex.*
import lateko.model.inline.StyledText
import lateko.renderer.common.InlineRenderVisitor

internal interface TexStyledTextRenderVisitor : InlineRenderVisitor {
	override fun visit(text: StyledText): String {
		return text.styles.let {
			when (true) {
				it.containsAll(setOf(StyledText.Style.Strong, StyledText.Style.Italic)) -> StrongItalicText(text.string)
				it.contains(StyledText.Style.Strong) -> BoldText(text.string)
				it.contains(StyledText.Style.Italic) -> ItalicText(text.string)
				it.contains(StyledText.Style.Typewriter) -> TypewriterText(text.string)
				it.contains(StyledText.Style.SmallCaps) -> SmallCapsText(text.string)
				else -> null
			}?.toString() ?: text.string
		}
	}
}