package lateko.dsl

import lateko.dsl.inline.text
import lateko.element.InlineElement
import lateko.element.Line
import lateko.element.LineComposition
import lateko.element.LineElement

open class LineScope : Builder<LineElement, LineComposition>() {
	operator fun InlineElement.unaryMinus() {
		elements += Line(this)
	}

	operator fun String.unaryMinus() {
		-this.text
	}

	override fun build(): LineComposition {
		return LineComposition(elements)
	}

	companion object
}

