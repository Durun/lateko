package io.github.durun.lateko.model.line

import io.github.durun.lateko.model.inline.InlineElement

interface Line : LineElement {
	val element: InlineElement
	override fun <R> accept(visitor: LineVisitor<R>): R = visitor.visit(this)

	companion object {
		fun InlineElement.toLine(): Line = LineData(this)
	}

	private data class LineData(override val element: InlineElement) : Line
}

