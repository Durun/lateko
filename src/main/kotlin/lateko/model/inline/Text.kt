package lateko.model.inline

import lateko.model.InlineElement
import lateko.visitor.InlineVisitor

data class Text(val text: String) : InlineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
}