package lateko.visitor

import lateko.element.*

interface DocumentRenderVisitor : StructureRenderVisitor, InlineRenderVisitor

interface StructureRenderVisitor : StructureVisitor<String> {
	val StructureElement.rendered: String
		get() = this.accept(this@StructureRenderVisitor)

	override fun visit(composition: StructureComposition): String {
		return composition.children.joinToString("") { it.rendered }
	}
}

interface InlineRenderVisitor : InlineVisitor<String> {
	val InlineElement.rendered: String
		get() = this.accept(this@InlineRenderVisitor)
	val LineElement.rendered: String
		get() = this.accept(this@InlineRenderVisitor)

	fun EmbeddedCode.isEnabled(): Boolean

	override fun visit(composition: InlineComposition): String {
		return composition.children.joinToString("") { it.rendered }
	}

	override fun visit(lines: LineComposition): String {
		return lines.children.joinToString("") { it.rendered }
	}

	override fun visit(code: EmbeddedCode): String = code.takeIf { it.isEnabled() }?.code.orEmpty()

	override fun visit(line: Line): String {
		val lineStr = line.element.accept(this) + "\n"
		return lineStr.takeUnless {
			line.element is EmbeddedCode && !line.element.isEnabled()
		}.orEmpty()
	}
}