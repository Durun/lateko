package io.github.durun.lateko.model.inline

import io.github.durun.lateko.target.tex.BeginEnd
import io.github.durun.lateko.target.tex.Caption
import io.github.durun.lateko.target.tex.SimpleTexCommand

class Image(
		val title: String,
		val path: String
) : InlineExtension {
	override fun renderTex(): String {
		return BeginEnd("figure") {
			"""
				${SimpleTexCommand("includegraphics", listOf("width=\\linewidth"), arg = path)}
				${Caption(title)}
			""".trimIndent()
		}.toString()
	}

	override fun renderMarkdown(): String = "![$title]($path)"
}