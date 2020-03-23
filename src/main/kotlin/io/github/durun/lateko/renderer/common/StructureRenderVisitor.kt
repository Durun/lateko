package io.github.durun.lateko.renderer.common

import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.structure.*

interface StructureRenderVisitor : StructureVisitor<String> {
	val outputFormat: Format
	fun StructureElement.rendered(): String
	fun Structure.rendered(): String = this.element.renderedAs(outputFormat)
	fun StructureExtension.rendered(): String = this.renderedAs(outputFormat)
	override fun visit(composition: StructureComposition): String {
		return composition.children.joinToString("") { it.accept(this@StructureRenderVisitor) }
	}
}