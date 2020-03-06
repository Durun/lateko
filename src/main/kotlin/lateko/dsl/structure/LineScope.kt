package lateko.dsl.structure

import lateko.dsl.Builder
import lateko.model.inline.InlineElement
import lateko.model.line.LineComposition
import lateko.model.line.LineComposition.Companion.toComposition
import lateko.model.line.LineElement

open class LineScope : Builder<LineElement>() {
	companion object {
		fun (LineScope.() -> Unit).build(): LineComposition {
			val builder = LineScope()
			builder.this()
			return builder.build()
		}
	}

	operator fun InlineElement.unaryMinus() = line(this)
	operator fun String.unaryMinus() = line(this)

	override fun build(): LineComposition = elements.toComposition()
}
