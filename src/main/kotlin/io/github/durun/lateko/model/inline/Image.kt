package io.github.durun.lateko.model.inline

import io.github.durun.lateko.model.Labeled
import io.github.durun.lateko.renderer.markdown.anchor
import io.github.durun.lateko.target.tex.*

class Image(
		private val title: String,
		private val path: String,
		override val label: String = path,
		private val width: String = "\\linewidth"
) : InlineExtension, Labeled {
	override fun renderPlane(): String = title
	override fun renderTex(): String {
		return BeginEnd("figure") {
			"""
				$Centering
				${SimpleTexCommand("includegraphics", listOf("width=$width"), arg = path)}
				${Caption(title)}${Label(label)}
			""".trimIndent()
		}.toString()
	}

	override fun renderMarkdown(): String = "![$title]($path)$anchor"
}