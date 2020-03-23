package io.github.durun.lateko.model.structure

import io.github.durun.lateko.model.inline.InlineElement

data class Chapter(
		val content: StructureElement,
		val name: InlineElement,
		private var idName: String? = null) : StructureElement {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
	override val id: String get() = idName ?: super.id
	fun changeId(newId: String) {
		idName = newId
	}
}