package lateko.dsl

import lateko.command.*
import lateko.element.EmbeddedCode
import lateko.element.Line
import lateko.element.LineComposition
import lateko.element.LineElement

open class TexHeaderScope() : Builder<LineElement, LineComposition>() {
	private val format = EmbeddedCode.Format.Tex
	private fun Command.toLine(): Line = Line(EmbeddedCode(this.toString(), format = format))

	fun documentClass(className: String, vararg options: String): Line {
		return DocumentClass(name = className, options = options.asList()).toLine().adding()
	}

	internal fun title(title: String): Line {
		return Title(title).toLine().adding()
	}

	fun author(author: String): Line = Author(author).toLine().adding()

	fun date(date: String): Line = Date(date).toLine().adding()

	override fun build(): LineComposition = LineComposition(elements)
}