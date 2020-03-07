package lateko.command.markdown

class Header(val level: Int, val text: String?) : MarkdownCommand {
	override fun toString(): String {
		if (level <= 0) throw IllegalArgumentException("level must be positive integer.")
		val escapedText = text.orEmpty()
				.trim()
				.takeIf { it.isNotEmpty() } ?: ""
		return "#".repeat(level) + " " + escapedText + "\n"
	}
}