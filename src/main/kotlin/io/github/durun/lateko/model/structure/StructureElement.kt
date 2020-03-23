package io.github.durun.lateko.model.structure

import io.github.durun.lateko.model.Element
import io.github.durun.lateko.model.inline.EmbeddedCode

interface StructureElement : Element {
	fun <R> accept(visitor: StructureVisitor<R>): R
	fun renderedAs(format: EmbeddedCode.Format): String
	val id: String get() = this.hashCode().toString()
}