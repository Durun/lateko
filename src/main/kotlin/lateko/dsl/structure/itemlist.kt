package lateko.dsl.structure

import lateko.dsl.line.IndexedItemListScope
import lateko.dsl.line.SimpleItemListScope
import lateko.model.line.ItemList

fun ParagraphScope.list(items: SimpleItemListScope.() -> Unit): ItemList {
	val builder = SimpleItemListScope()
	builder.items()
	return builder.build().adding()
}

fun ParagraphScope.listIndexed(items: IndexedItemListScope.() -> Unit): ItemList {
	val builder = IndexedItemListScope()
	builder.items()
	return builder.build().adding()
}