package lateko.dsl.inline

import lateko.dsl.Builder
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

	operator fun InlineElement.unaryMinus() = line(this)
	operator fun String.unaryMinus() = -this.text

	override fun build(): LineComposition = LineComposition(elements)
}

fun LineScope.line(element: InlineElement) = Line(element).adding()

