package lateko.element

import lateko.visitor.InlineVisitor
import lateko.visitor.StructureVisitor

interface Element

interface InlineElement : Element {
	fun <R> accept(visitor: InlineVisitor<R>): R
}

interface StructureElement : Element {
	fun <R> accept(visitor: StructureVisitor<R>): R
}