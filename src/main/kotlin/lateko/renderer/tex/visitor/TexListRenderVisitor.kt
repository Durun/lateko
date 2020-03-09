package lateko.renderer.tex.visitor

import lateko.command.tex.Begin
import lateko.command.tex.End
import lateko.model.line.IndexedItem
import lateko.model.line.ItemList
import lateko.model.line.SimpleItem
import lateko.renderer.common.LineRenderVisitor

internal interface TexListRenderVisitor : LineRenderVisitor {
	override fun visit(item: SimpleItem): String = "\\item ${item.element.accept(inlineRenderVisitor)}"
	override fun visit(item: IndexedItem): String = "\\item ${item.element.accept(inlineRenderVisitor)}"
	override fun visit(list: ItemList): String {    // TODO
		val items = list.items
		val envName = when (true) {
			items.all { it is SimpleItem } -> "itemize"
			items.all { it is IndexedItem } -> "enumerate"
			else -> throw IllegalStateException()
		}
		return "${Begin(envName)}\n" +
				items.joinToString("\n") { it.accept(this) } + "\n" +
				End(envName)
	}
}