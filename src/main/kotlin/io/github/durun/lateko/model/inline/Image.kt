package io.github.durun.lateko.model.inline

import io.github.durun.lateko.model.Labeled
import io.github.durun.lateko.renderer.markdown.anchor
import io.github.durun.lateko.target.tex.BeginEnd
import io.github.durun.lateko.target.tex.Caption
import io.github.durun.lateko.target.tex.Label
import io.github.durun.lateko.target.tex.SimpleTexCommand

class Image(
		val title: String,
		val path: String,
		override val label: String = path
) : InlineExtension, Labeled {
	override fun renderPlane(): String = title
	override fun renderTex(): String {
		return BeginEnd("figure") {
			"""
				${Label(label)}
				${SimpleTexCommand("includegraphics", listOf("width=\\linewidth"), arg = path)}
				${Caption(title)}
			""".trimIndent()
		}.toString()
	}

	override fun renderMarkdown(): String = "![$title]($path)$anchor"
}