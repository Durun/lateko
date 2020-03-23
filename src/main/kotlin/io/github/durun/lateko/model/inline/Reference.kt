package io.github.durun.lateko.model.inline

import io.github.durun.lateko.model.structure.StructureElement

class Reference(val element: StructureElement) : InlineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
	val id: String get() = element.id
}