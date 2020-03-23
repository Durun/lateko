package io.github.durun.lateko.model.line

import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.inline.InlineElement

interface Line : LineElement {
	val element: InlineElement
	override fun <R> accept(visitor: LineVisitor<R>): R = visitor.visit(this)
	override fun renderedAs(format: Format): String {
		val lineStr = element.renderedAs(format) + "\n"
		return lineStr.takeUnless {
			val e = element
			e is EmbeddedCode && e.format != format
		}.orEmpty()
	}

	companion object {
		fun InlineElement.toLine(): Line = LineData(this)
	}

	private data class LineData(override val element: InlineElement) : Line
}

