package io.github.durun.lateko.model.inline

import io.github.durun.lateko.model.Format
import io.github.durun.lateko.renderer.markdown.MarkdownEscaper
import io.github.durun.lateko.renderer.tex.TexEscaper

data class Text(val text: String) : InlineExtension {
	override fun renderedAs(format: Format): String = when (format) {
		Format.Markdown -> MarkdownEscaper.escape(text)
		Format.Tex -> TexEscaper.escape(text)
		else -> text
	}
}