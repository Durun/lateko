package io.github.durun.lateko.renderer.tex.visitor

import io.github.durun.lateko.command.tex.*
import io.github.durun.lateko.model.inline.StyledText
import io.github.durun.lateko.renderer.common.InlineRenderVisitor

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