package lateko.model.inline

import lateko.model.Element

interface InlineElement : Element {
	fun <R> accept(visitor: InlineVisitor<R>): R
}