package lateko.renderer.tex.visitor

import lateko.command.tex.Begin
import lateko.command.tex.End
import lateko.model.line.DescriptionItem
import lateko.model.line.IndexedItem
import lateko.model.line.ItemList
import lateko.model.line.SimpleItem
import lateko.renderer.common.LineRenderVisitor

internal interface TexListRenderVisitor : LineRenderVisitor {
	override fun visit(item: SimpleItem): String = "\\item ${item.element.accept(inlineRenderVisitor)}"
	override fun visit(item: IndexedItem): String = "\\item ${item.element.accept(inlineRenderVisitor)}"
	override fun visit(item: DescriptionItem): String {
		val title = item.title.accept(inlineRenderVisitor)
		val element = item.element.accept(inlineRenderVisitor)
		return if (item.lineBreak)
			"\\item[$title]\\mbox{}\\\\$element"
		else
			"\\item[$title]$element"
	}

	override fun visit(list: ItemList): String {    // TODO
		val items = list.items
		val leafItems = items.filterNot { it is ItemList }
		val envName = when (true) {
			leafItems.all { it is SimpleItem } -> "itemize"
			leafItems.all { it is IndexedItem } -> "enumerate"
			leafItems.all { it is DescriptionItem } -> "description"
			else -> throw IllegalStateException()
		}
		return "${Begin(envName)}\n" +
				items.joinToString("\n") { it.accept(this) } + "\n" +
				End(envName)
	}
}