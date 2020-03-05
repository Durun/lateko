package lateko.visitor

import lateko.element.EmbeddedCode
import lateko.element.InlineElement
import lateko.element.LineElement
import lateko.element.StructureElement

interface DocumentRenderVisitor : StructureRenderVisitor, InlineRenderVisitor
interface StructureRenderVisitor : StructureVisitor<String> {
	val StructureElement.rendered: String
		get() = this.accept(this@StructureRenderVisitor)
}

interface InlineRenderVisitor : InlineVisitor<String> {
	val InlineElement.rendered: String
		get() = this.accept(this@InlineRenderVisitor)
	val LineElement.rendered: String
		get() = this.accept(this@InlineRenderVisitor)

	fun EmbeddedCode.isEnabled(): Boolean
}