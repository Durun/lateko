package io.github.durun.lateko.extension

import io.github.durun.lateko.target.markdown.BoldItalicText
import io.github.durun.lateko.target.markdown.BoldText
import io.github.durun.lateko.target.markdown.CodeText
import io.github.durun.lateko.target.markdown.ItalicText
import io.github.durun.lateko.target.tex.SmallCapsText
import io.github.durun.lateko.target.tex.StrongItalicText
import io.github.durun.lateko.target.tex.TypewriterText
import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.inline.InlineExtension
import io.github.durun.lateko.model.inline.Text

data class StyledText(val text: Text, val styles: Set<Style>) : InlineExtension {
	val string: String = text.text

	enum class Style {
		Strong,
		Italic,
		Typewriter,
		SmallCaps
	}

	override fun renderedAs(format: Format): String = when (format) {
		Format.Markdown -> styles.let {
			when (true) {
				it.containsAll(setOf(Style.Strong, Style.Italic)) -> BoldItalicText(string)
				it.contains(Style.Strong) -> BoldText(string)
				it.contains(Style.Italic) -> ItalicText(string)
				it.contains(Style.Typewriter) -> CodeText(string)
				else -> null
			}?.toString() ?: string
		}

		Format.Tex -> styles.let {
			when (true) {
				it.containsAll(setOf(Style.Strong, Style.Italic)) -> StrongItalicText(string)
				it.contains(Style.Strong) -> io.github.durun.lateko.target.tex.BoldText(string)
				it.contains(Style.Italic) -> io.github.durun.lateko.target.tex.ItalicText(string)
				it.contains(Style.Typewriter) -> TypewriterText(string)
				it.contains(Style.SmallCaps) -> SmallCapsText(string)
				else -> null
			}?.toString() ?: string
		}

		else -> string
	}
}