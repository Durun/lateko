package io.github.durun.lateko.target.markdown

class Header(val level: Int, val text: String?) : MarkdownCommand {
	override fun toString(): String {
		if (level <= 0) throw IllegalArgumentException("level must be positive integer.")
		val escapedText = text.orEmpty()
				.trim()
				.takeIf { it.isNotEmpty() } ?: ""
		return "#".repeat(level) + " " + escapedText + "\n"
	}
}

class Anchor(private val id: Any) : MarkdownCommand {
	override fun toString(): String = """<div id="${escapeAnchor(id.toString())}"></div>"""

	companion object {
		fun escapeAnchor(id: String): String {
			return id.toLowerCase().replace(Regex("[^a-z0-9]"), "")
		}
	}
}

class AnchorLink(val url: String, val element: String = url) : MarkdownCommand {
	override fun toString(): String = """<a href="#${Anchor.escapeAnchor(url)}">$element</a>"""
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

class CodeText(val text: String) : MarkdownCommand {
	override fun toString(): String {
		return "`$text`"
	}
}