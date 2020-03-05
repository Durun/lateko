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
}