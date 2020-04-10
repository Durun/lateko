package io.github.durun.lateko.model.structure

import io.github.durun.lateko.model.Labeled
import io.github.durun.lateko.model.inline.InlineElement

data class Chapter(
		val content: StructureElement,
		val name: InlineElement,
		private var idName: String? = null,
		override val context: StructureContext? = null
) : StructureElement, Labeled {
	override fun <R> accept(visitor: StructureVisitor<R>): R = visitor.visit(this)
	override val id: String get() = idName ?: super.id
	fun isIdDefault(): Boolean = idName == null
	fun changeId(newId: String) {
		idName = newId
	}

	override val label: String get() = id
}
