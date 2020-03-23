package io.github.durun.lateko.model.structure

import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.line.LineElement

data class Paragraph(val content: LineElement) : StructureExtension {
	override fun renderedAs(format: EmbeddedCode.Format): String = when (format) {
		EmbeddedCode.Format.Markdown -> content.renderedAs(format) + "\n"
		EmbeddedCode.Format.Tex -> content.renderedAs(format) + "\n"
		else -> TODO()
	}
}