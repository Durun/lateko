package lateko.renderer.markdown

import lateko.renderer.common.Escaper

object MarkdownEscaper : Escaper {
	private val escapeMap = listOf(
			"\\" to "\\\\",
			"#" to "\\#",
			"*" to "\\*",
			"_" to "\\_",
			"`" to "\\`"
	)

	override fun escape(text: String): String {
		return escapeMap.fold(text) { acc, (before, after) ->
			acc.replace(before, after)
		}
	}
}