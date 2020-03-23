package io.github.durun.lateko.model.inline

import io.github.durun.lateko.command.markdown.Link
import io.github.durun.lateko.command.tex.SimpleTexCommand
import io.github.durun.lateko.dsl.inline.text

data class UrlText(
		val url: String,
		val text: InlineElement = url.text
) : InlineExtension {
	override fun renderedAs(format: EmbeddedCode.Format): String? = when (format) {
		EmbeddedCode.Format.Tex -> {
			val text = text.renderedAs(format).orEmpty()
			val url = url
			if (text == url)
				SimpleTexCommand("url", arg = url).toString()
			else
				SimpleTexCommand("href", args = listOf(url, text)).toString()
		}
		EmbeddedCode.Format.Markdown -> Link(url = url, element = this.text.renderedAs(format).orEmpty()).toString()
		else -> null
	}
}