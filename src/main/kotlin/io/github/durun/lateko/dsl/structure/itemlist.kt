package io.github.durun.lateko.dsl.structure

import io.github.durun.lateko.dsl.Builder
import io.github.durun.lateko.dsl.line.DescriptionItemListScope
import io.github.durun.lateko.dsl.line.IndexedItemListScope
import io.github.durun.lateko.dsl.line.SimpleItemListScope
import io.github.durun.lateko.model.line.ItemList
import io.github.durun.lateko.model.line.ListItem

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