package lateko.renderer.markdown.visitor

import lateko.model.line.ItemList
import lateko.model.line.SimpleItem
import lateko.renderer.common.LineRenderVisitor

internal interface MarkdownListRenderVisitor : LineRenderVisitor {
	override fun visit(item: SimpleItem): String = "- ${item.element.accept(inlineRenderVisitor)}"
	override fun visit(list: ItemList): String {
		return list.items.joinToString("\n") {
			val rendered = it.accept(this)
			if (it is ItemList)
				rendered.prependIndent("\t")
			else
				rendered
		}
	}
}