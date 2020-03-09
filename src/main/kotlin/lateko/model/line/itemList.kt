package lateko.model.line

import lateko.model.inline.InlineElement

interface ListItem : LineElement

class SimpleItem(val element: InlineElement) : ListItem {
	override fun <R> accept(visitor: LineVisitor<R>): R = visitor.visit(this)
}

abstract class ItemList(val items: List<ListItem>) : ListItem {
	override fun <R> accept(visitor: LineVisitor<R>): R = visitor.visit(this)
}