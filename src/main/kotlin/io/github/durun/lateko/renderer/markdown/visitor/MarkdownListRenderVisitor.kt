package io.github.durun.lateko.renderer.markdown.visitor

import io.github.durun.lateko.model.line.DescriptionItem
import io.github.durun.lateko.model.line.IndexedItem
import io.github.durun.lateko.model.line.ItemList
import io.github.durun.lateko.model.line.SimpleItem
import io.github.durun.lateko.renderer.common.LineRenderVisitor

internal interface MarkdownListRenderVisitor : LineRenderVisitor {
	override fun visit(item: SimpleItem): String = "- ${item.element.accept(inlineRenderVisitor)}"
	override fun visit(item: IndexedItem): String = "1. ${item.element.accept(inlineRenderVisitor)}"
	override fun visit(item: DescriptionItem): String {
		val title = item.title.accept(inlineRenderVisitor)
		val element = item.element.accept(inlineRenderVisitor)
		return if (item.lineBreak)
			"- $title\n\n  $element\n"
		else
			"- $title $element"
	}

	override fun visit(list: ItemList): String {
		val items = list.items
		val suffix = items.lastOrNull().let {
			if (it is DescriptionItem && it.lineBreak) "\n"
			else "\n\n"
		}
		val listString = items.joinToString("\n") {
			val rendered = it.accept(this)
			if (it is ItemList)
				rendered.prependIndent("\t")
			else
				rendered
		} + suffix
		return listString
				.replace(Regex("\t+\n"), "\n") // remove redundant tabs
				.replace("\n\n\n", "\n")
	}
}