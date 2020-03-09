package lateko.model.line

import lateko.model.Element

interface LineElement : Element {
	fun <R> accept(visitor: LineVisitor<R>): R
}