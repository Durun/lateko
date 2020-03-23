package io.github.durun.lateko.renderer.common

import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.line.Line
import io.github.durun.lateko.model.line.LineComposition
import io.github.durun.lateko.model.line.LineElement
import io.github.durun.lateko.model.line.LineVisitor

interface LineRenderVisitor : LineVisitor<String> {
	val inlineRenderVisitor: InlineRenderVisitor

	val LineElement.rendered: String
		get() = this.accept(this@LineRenderVisitor)

	fun outputFormat(): EmbeddedCode.Format

	override fun visit(lines: LineComposition): String {
		return lines.children.joinToString("") { it.rendered }
	}

	override fun visit(line: Line): String {
		val lineStr = line.element.accept(inlineRenderVisitor) + "\n"
		return lineStr.takeUnless {
			val e = line.element
			e is EmbeddedCode && e.format != outputFormat()
		}.orEmpty()
	}
}