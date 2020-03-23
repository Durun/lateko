package io.github.durun.lateko.renderer.markdown.visitor

import io.github.durun.lateko.command.markdown.BoldItalicText
import io.github.durun.lateko.command.markdown.BoldText
import io.github.durun.lateko.command.markdown.CodeText
import io.github.durun.lateko.command.markdown.ItalicText
import io.github.durun.lateko.model.inline.StyledText
import io.github.durun.lateko.renderer.common.InlineRenderVisitor

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