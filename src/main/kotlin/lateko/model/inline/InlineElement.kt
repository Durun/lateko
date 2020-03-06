package lateko.model.inline

import lateko.model.Element
import lateko.visitor.InlineVisitor

interface InlineElement : Element {
	fun <R> accept(visitor: InlineVisitor<R>): R
}