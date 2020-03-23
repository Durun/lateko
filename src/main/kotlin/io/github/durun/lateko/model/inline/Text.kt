package io.github.durun.lateko.model.inline

import io.github.durun.lateko.renderer.markdown.MarkdownEscaper
import io.github.durun.lateko.renderer.tex.TexEscaper

data class Text(val text: String) : InlineExtension {
	override fun renderedAs(format: EmbeddedCode.Format): String = when (format) {
		EmbeddedCode.Format.Markdown -> MarkdownEscaper.escape(text)
		EmbeddedCode.Format.Tex -> TexEscaper.escape(text)
		else -> text
	}
}