package lateko.renderer.tex

import lateko.renderer.Escaper

object TexEscaper : Escaper {
	private val escapeMap = listOf(
			"\\" to "\\\\",
			"$" to "\\$"
			// TODO
	)

	override fun escape(text: String): String {
		return escapeMap.fold(text) { text, (before, after) ->
			text.replace(before, after)
		}
	}
}