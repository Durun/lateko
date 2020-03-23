package io.github.durun.lateko.extension

import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.line.LineElement
import io.github.durun.lateko.model.structure.StructureExtension

data class Paragraph(val content: LineElement) : StructureExtension {
	override fun renderedAs(format: Format): String = when (format) {
		Format.Markdown -> content.renderedAs(format) + "\n"
		Format.Tex -> content.renderedAs(format) + "\n"
		else -> TODO()
	}
}