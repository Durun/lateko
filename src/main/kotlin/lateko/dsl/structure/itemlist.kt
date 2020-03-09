package lateko.dsl.structure

import lateko.dsl.Builder
import lateko.dsl.line.DescriptionItemListScope
import lateko.dsl.line.IndexedItemListScope
import lateko.dsl.line.SimpleItemListScope
import lateko.model.line.ItemList
import lateko.model.line.ListItem

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

fun ParagraphScope.listDescription(lineBreak: Boolean = true, items: DescriptionItemListScope.() -> Unit): ItemList {
	val builder = DescriptionItemListScope(lineBreak)
	builder.items()
	return builder.build().adding()
}

fun Builder<ListItem>.list(items: SimpleItemListScope.() -> Unit): ItemList {
	val builder = SimpleItemListScope()
	builder.items()
	return builder.build().adding()
}

fun Builder<ListItem>.listIndexed(items: IndexedItemListScope.() -> Unit): ItemList {
	val builder = IndexedItemListScope()
	builder.items()
	return builder.build().adding()
}

fun Builder<ListItem>.listDescription(lineBreak: Boolean = true, items: DescriptionItemListScope.() -> Unit): ItemList {
	val builder = DescriptionItemListScope(lineBreak)
	builder.items()
	return builder.build().adding()
}