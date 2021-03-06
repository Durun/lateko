package io.github.durun.lateko.renderer.markdown

import io.github.durun.lateko.renderer.common.Escaper

object MarkdownEscaper : Escaper {
	private val escapeMap = listOf(
			"\\" to "\\\\",
			"#" to "\\#",
			"*" to "\\*",
			"_" to "\\_",
			"`" to "\\`",
			"\n" to " "
	)

	override fun escape(text: String): String {
		return escapeMap.fold(text) { acc, (before, after) ->
			acc.replace(before, after)
		}
	}
}