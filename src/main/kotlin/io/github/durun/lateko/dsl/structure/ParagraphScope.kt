package io.github.durun.lateko.dsl.structure

import io.github.durun.lateko.dsl.Builder
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.line.LineComposition
import io.github.durun.lateko.model.line.LineComposition.Companion.toComposition
import io.github.durun.lateko.model.line.LineElement

open class ParagraphScope : Builder<LineElement>() {
	companion object {
		fun (ParagraphScope.() -> Unit).build(): LineComposition {
			val builder = ParagraphScope()
			builder.this()
			return builder.build()
		}
	}

	operator fun InlineElement.unaryMinus() = line(this)
	operator fun String.unaryMinus() = line(this)

	override fun build(): LineComposition = elements.toComposition()
}
