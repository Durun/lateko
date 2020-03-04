package lateko.dsl

import lateko.element.*

open class InlineScope : Builder<InlineElement, InlineComposition>() {

	override fun build(): InlineComposition = InlineComposition(elements)
}

