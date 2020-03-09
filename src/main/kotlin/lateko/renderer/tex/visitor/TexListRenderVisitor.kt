package lateko.renderer.tex.visitor

import lateko.command.tex.Begin
import lateko.command.tex.End
import lateko.model.line.ItemList
import lateko.model.line.SimpleItem
import lateko.renderer.common.LineRenderVisitor

internal interface TexListRenderVisitor : LineRenderVisitor {
	override fun visit(item: SimpleItem): String = "\\item ${item.element.accept(inlineRenderVisitor)}"
	override fun visit(list: ItemList): String {    // TODO
		val envName = "itemize"
		return "${Begin(envName)}\n" +
				list.items.joinToString("\n") { it.accept(this) } + "\n" +
				End(envName)
	}
}