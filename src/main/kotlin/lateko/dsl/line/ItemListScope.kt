package lateko.dsl.line

import lateko.dsl.Builder
import lateko.dsl.inline.text
import lateko.model.inline.InlineElement
import lateko.model.line.IndexedItem
import lateko.model.line.ItemList
import lateko.model.line.ItemList.Companion.toComposition
import lateko.model.line.ListItem
import lateko.model.line.SimpleItem

abstract class ItemListScope<I : ListItem> : Builder<ListItem>() {
	abstract fun item(element: InlineElement): I

	fun item(text: String): I = item(text.text)
	operator fun InlineElement.unaryMinus(): I = item(this)
	operator fun String.unaryMinus(): I = item(this)

	override fun build(): ItemList {
		return elements.toComposition()
	}
}

class SimpleItemListScope : ItemListScope<SimpleItem>() {
	override fun item(element: InlineElement): SimpleItem {
		return SimpleItem(element).adding()
	}
}

class IndexedItemListScope : ItemListScope<IndexedItem>() {
	override fun item(element: InlineElement): IndexedItem {
		return IndexedItem(element).adding()
	}
}