package lateko.dsl

import lateko.element.*

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
}

