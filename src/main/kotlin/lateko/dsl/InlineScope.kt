package lateko.dsl

import lateko.element.InlineComposition
import lateko.element.InlineElement

open class InlineScope : Builder<InlineElement, InlineComposition>() {
	operator fun InlineElement.unaryMinus() {
		elements += this
	}

	operator fun String.unaryMinus() {
		-this.text
	}

	override fun build(): InlineComposition = InlineComposition(elements)
}

