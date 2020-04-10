package io.github.durun.lateko.model.structure

import io.github.durun.lateko.model.inline.InlineElement

data class Section(
		val content: StructureElement,
		val name: InlineElement? = null,
		private var idName: String? = null,
		override val context: StructureContext? = null
) : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
	override val id: String get() = idName ?: super.id
	fun changeId(newId: String) {
		idName = newId
	}
}
