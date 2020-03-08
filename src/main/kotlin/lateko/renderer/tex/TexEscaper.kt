package lateko.renderer.tex

import lateko.renderer.common.Escaper

object TexEscaper : Escaper {
	private val escapeMap = listOf(
			"#" to "\\#",
			"$" to "\\$",
			"%" to "\\%",
			"&" to "\\&",
			"_" to "\\_",
			"|" to "\\textbar",
			"<" to "\\textless",
			">" to "\\textgreater",
			"^" to "\\textasciitilde",
			"~" to "\\textasciicircum"
	)

	private fun String.escapeCore(): String {
		return this
				.replace("\\", "\\textbackslash")
				.replace("{", "\\{")
				.replace("}", "\\}")
				.replace("\\textbackslash", "{\\textbackslash}")
	}

	override fun escape(text: String): String {

		return escapeMap.fold(text.escapeCore()) { acc, (before, after) ->
			acc.replace(before, "{$after}")
		}
	}
}