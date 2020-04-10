package io.github.durun.lateko.model.inline

import io.github.durun.lateko.renderer.markdown.MarkdownEscaper
import io.github.durun.lateko.renderer.tex.TexEscaper

data class Text(val text: String) : InlineExtension {
	override fun renderPlane(): String = text
	override fun renderTex(): String = TexEscaper.escape(text)
	override fun renderMarkdown(): String = MarkdownEscaper.escape(text)
}