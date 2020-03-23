package io.github.durun.lateko.dsl.line

import io.github.durun.lateko.dsl.Builder
import io.github.durun.lateko.dsl.inline.text
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.line.*
import io.github.durun.lateko.model.line.ItemList.Companion.toComposition

abstract class ItemListScope<I : ListItem> : Builder<ListItem>() {
	fun item(text: String): I = item(text.text)
	abstract fun item(element: InlineElement): I

	operator fun InlineElement.unaryMinus(): I = item(this)
	operator fun String.unaryMinus(): I = item(this)

	override fun build(): ItemList = elements.toComposition()
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

class DescriptionItemListScope(private val lineBreak: Boolean = true) : Builder<ListItem>() {
	fun item(title: String, element: String): DescriptionItem = item(title.text, element.text)
	fun item(title: InlineElement, element: InlineElement): DescriptionItem {
		return DescriptionItem(title, element, lineBreak = lineBreak).adding()
	}


	override fun build(): ItemList = elements.toComposition()
}