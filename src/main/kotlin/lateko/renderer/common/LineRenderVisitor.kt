package lateko.renderer.common

import lateko.model.inline.EmbeddedCode
import lateko.model.line.Line
import lateko.model.line.LineComposition
import lateko.model.line.LineElement
import lateko.model.line.LineVisitor

interface LineRenderVisitor : LineVisitor<String> {
	val inlineRenderVisitor: InlineRenderVisitor

	val LineElement.rendered: String
		get() = this.accept(this@LineRenderVisitor)

	fun EmbeddedCode.isEnabled(): Boolean

	override fun visit(lines: LineComposition): String {
		return lines.children.joinToString("") { it.rendered }
	}

	override fun visit(line: Line): String {
		val lineStr = line.element.accept(inlineRenderVisitor) + "\n"
		return lineStr.takeUnless {
			val e = line.element
			e is EmbeddedCode && !e.isEnabled()
		}.orEmpty()
	}
}