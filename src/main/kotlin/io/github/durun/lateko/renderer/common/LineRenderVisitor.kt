package io.github.durun.lateko.renderer.common

import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.line.*

interface LineRenderVisitor : LineVisitor<String> {
	val InlineElement.rendered: String
		get() = this.renderedAs(outputFormat())
	val LineElement.rendered: String
		get() = this.renderedAs(outputFormat())

	fun outputFormat(): EmbeddedCode.Format

	override fun visit(lines: LineComposition): String {
		return lines.children.joinToString("") { it.rendered }
	}

	override fun visit(line: Line): String {
		val lineStr = line.element.rendered + "\n"
		return lineStr.takeUnless {
			val e = line.element
			e is EmbeddedCode && e.format != outputFormat()
		}.orEmpty()
	}

	override fun visit(lines: LineExtension): String = lines.renderedAs(outputFormat())

}