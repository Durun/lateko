package lateko.dsl.header

import lateko.command.*
import lateko.dsl.Builder
import lateko.element.EmbeddedCode
import lateko.element.Line
import lateko.element.Line.Companion.toLine
import lateko.element.LineComposition
import lateko.element.LineComposition.Companion.toComposition
import lateko.element.LineElement

open class TexHeaderScope : Builder<LineElement, LineComposition>() {
	companion object {
		private val defaultDocumentClass = DocumentClass(name = "jsbook", options = listOf("a4paper", "11pt", "oneside", "openany", "report"))
	}

	private val format = EmbeddedCode.Format.Tex
	private fun Command.toLine(): Line = EmbeddedCode(this.toString(), format = format).toLine()

	private var documentClass = defaultDocumentClass

	fun documentClass(className: String, vararg options: String) {
		documentClass = DocumentClass(name = className, options = options.asList())
	}

	internal fun title(title: String): Line {
		return Title(title).toLine().adding()
	}

	fun author(author: String): Line = Author(author).toLine().adding()

	fun date(date: String): Line = Date(date).toLine().adding()

	override fun build(): LineComposition {
		documentClass.toLine().addingFirst()
		return elements.toComposition()
	}
}