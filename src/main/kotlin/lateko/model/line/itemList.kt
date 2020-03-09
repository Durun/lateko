package lateko.model.line

import lateko.model.Composition
import lateko.model.inline.InlineElement

interface ListItem : LineElement

class SimpleItem(val element: InlineElement) : ListItem {
	override fun <R> accept(visitor: LineVisitor<R>): R = visitor.visit(this)
}

class IndexedItem(val element: InlineElement) : ListItem {
	override fun <R> accept(visitor: LineVisitor<R>): R = visitor.visit(this)
}

class DescriptionItem(
		val title: InlineElement,
		val element: InlineElement,
		val lineBreak: Boolean = true) : ListItem {
	override fun <R> accept(visitor: LineVisitor<R>): R = visitor.visit(this)
}

interface ItemList : ListItem, Composition<ListItem> {
	val items: List<ListItem>
	override fun <R> accept(visitor: LineVisitor<R>): R = visitor.visit(this)

	companion object {
		fun Iterable<ListItem>.toComposition(): ItemList {
			return ItemListData(this.toList())
		}
	}

	private data class ItemListData(override val items: List<ListItem>) : ItemList
}