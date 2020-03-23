package io.github.durun.lateko.renderer.common

import io.github.durun.lateko.model.structure.StructureComposition
import io.github.durun.lateko.model.structure.StructureElement
import io.github.durun.lateko.model.structure.StructureVisitor

interface StructureRenderVisitor : StructureVisitor<String> {
	val StructureElement.rendered: String
		get() = this.accept(this@StructureRenderVisitor)

	override fun visit(composition: StructureComposition): String {
		return composition.children.joinToString("") { it.rendered }
	}
}