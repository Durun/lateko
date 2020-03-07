package lateko.renderer.common

import lateko.model.structure.StructureComposition
import lateko.model.structure.StructureElement
import lateko.model.structure.StructureVisitor

interface StructureRenderVisitor : StructureVisitor<String> {
	val StructureElement.rendered: String
		get() = this.accept(this@StructureRenderVisitor)

	override fun visit(composition: StructureComposition): String {
		return composition.children.joinToString("") { it.rendered }
	}
}