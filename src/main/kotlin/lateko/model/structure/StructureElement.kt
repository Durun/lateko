package lateko.model.structure

import lateko.model.Element

interface StructureElement : Element {
	fun <R> accept(visitor: StructureVisitor<R>): R
	val id: String get() = this.hashCode().toString()
}