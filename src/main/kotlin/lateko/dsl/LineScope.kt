package lateko.dsl

import lateko.dsl.inline.text
import lateko.element.InlineElement
import lateko.element.Line
import lateko.element.LineComposition
import lateko.element.LineElement

open class LineScope : Builder<LineElement, LineComposition>() {
	companion object {
		fun (LineScope.() -> Unit).build(): LineComposition {
			val builder = LineScope()
			builder.this()
			return builder.build()
		}
	}

	operator fun InlineElement.unaryMinus() {
		elements += Line(this)
	}

	operator fun String.unaryMinus() {
		-this.text
	}

	override fun build(): LineComposition = LineComposition(elements)
}

