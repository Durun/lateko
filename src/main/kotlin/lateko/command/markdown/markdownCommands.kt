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

class Link(val url: String, val element: String = url) : MarkdownCommand {
	override fun toString(): String = "[$element]($url)"
}

class Image(val url: String) : MarkdownCommand {
	override fun toString(): String = "![]($url)"
}

class BoldText(val text: String) : MarkdownCommand {
	override fun toString(): String {
		return if (text.contains("*")) "__${text}__"
		else "**$text**"
	}
}

class ItalicText(val text: String) : MarkdownCommand {
	override fun toString(): String {
		return if (text.contains("*")) "_${text}_"
		else "*$text*"
	}
}

class BoldItalicText(val text: String) : MarkdownCommand {
	override fun toString(): String {
		return if (text.contains("*")) "___${text}___"
		else "***$text***"
	}
}