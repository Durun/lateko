package lateko.renderer.tex

import lateko.renderer.common.Escaper

object TexEscaper : Escaper {
	private val escapeMap = listOf(
			"\\" to "\\\\",
			"$" to "\\$"
			// TODO
	)

	override fun escape(text: String): String {
		return escapeMap.fold(text) { acc, (before, after) ->
			acc.replace(before, after)
		}
	}
}