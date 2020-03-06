package lateko.model.line

import lateko.model.Element
import lateko.visitor.InlineVisitor

interface LineElement : Element {
	fun <R> accept(visitor: InlineVisitor<R>): R
}