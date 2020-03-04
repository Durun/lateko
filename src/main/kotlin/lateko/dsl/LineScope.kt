package lateko.dsl

import lateko.element.*

open class LineScope : Builder<LineElement, LineComposition>() {
	operator fun InlineElement.unaryMinus() {
		elements += Line(this)
	}

	operator fun String.unaryMinus() {
		-this.text
	}


	fun embed(format: EmbeddedCode.Format, code: String): EmbeddedCode {
		val element = code.embed(format)
		elements += Line(element)
		return element
	}

	fun String.embed(format: EmbeddedCode.Format): EmbeddedCode {
		return EmbeddedCode(format = format, code = this)
	}

	fun markdown(code: String): EmbeddedCode = embed(EmbeddedCode.Format.Markdown, code = code)
	fun String.asMarkdown(): EmbeddedCode = this.embed(EmbeddedCode.Format.Markdown)
	fun String.asTex(): EmbeddedCode = this.embed(EmbeddedCode.Format.Tex)

	override fun build(): LineComposition {
		return LineComposition(elements)
	}
}

