package io.github.durun.lateko.renderer.common

import io.github.durun.lateko.model.structure.StructureComposition
import io.github.durun.lateko.model.structure.StructureVisitor

interface StructureRenderVisitor : StructureVisitor<String> {
	override fun visit(composition: StructureComposition): String {
		return composition.children.joinToString("") { it.accept(this@StructureRenderVisitor) }
	}
}