package io.github.durun.lateko.model.structure

interface StructureExtension : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
}