package io.github.durun.lateko.extension

import io.github.durun.lateko.dsl.inline.text
import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.inline.InlineExtension
import io.github.durun.lateko.target.markdown.Link
import io.github.durun.lateko.target.tex.SimpleTexCommand

data class UrlText(
		val url: String,
		val text: InlineElement = url.text
) : InlineExtension {
	override fun renderPlane(): String = url
	override fun renderTex(): String {
		val text = text.renderedAs(Format.Tex)
		val url = url
		return if (text == url)
			SimpleTexCommand("url", arg = url).toString()
		else
			SimpleTexCommand("href", args = listOf(url, text)).toString()
	}

	override fun renderMarkdown(): String {
		return Link(url = url, element = this.text.renderedAs(Format.Markdown)).toString()
	}
}