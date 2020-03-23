package io.github.durun.lateko.extension

import io.github.durun.lateko.command.markdown.Link
import io.github.durun.lateko.command.tex.SimpleTexCommand
import io.github.durun.lateko.dsl.inline.text
import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.inline.InlineExtension

data class UrlText(
		val url: String,
		val text: InlineElement = url.text
) : InlineExtension {
	override fun renderedAs(format: Format): String = when (format) {
		Format.Tex -> {
			val text = text.renderedAs(format)
			val url = url
			if (text == url)
				SimpleTexCommand("url", arg = url).toString()
			else
				SimpleTexCommand("href", args = listOf(url, text)).toString()
		}
		Format.Markdown -> Link(url = url, element = this.text.renderedAs(format)).toString()
		else -> "${text.renderedAs(format)}($url)"
	}
}