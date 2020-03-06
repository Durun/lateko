package lateko.model.structure

import lateko.model.Element
import lateko.visitor.StructureVisitor

interface StructureElement : Element {
	fun <R> accept(visitor: StructureVisitor<R>): R
}