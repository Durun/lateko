package io.github.durun.lateko.model.structure

import io.github.durun.lateko.model.Format

interface StructureExtension : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
	fun renderedAs(format: Format): String
}