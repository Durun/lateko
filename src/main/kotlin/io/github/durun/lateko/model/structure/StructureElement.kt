package io.github.durun.lateko.model.structure

import io.github.durun.lateko.model.Element

interface StructureElement : Element {
	fun <R> accept(visitor: StructureVisitor<R>): R
	val id: String get() = this.hashCode().toString()
}