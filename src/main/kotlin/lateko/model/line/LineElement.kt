package lateko.model.line

import lateko.model.Element
import lateko.model.inline.InlineVisitor

interface LineElement : Element {
	fun <R> accept(visitor: InlineVisitor<R>): R
}