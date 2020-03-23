package io.github.durun.lateko.model.line

import io.github.durun.lateko.command.tex.Begin
import io.github.durun.lateko.command.tex.End
import io.github.durun.lateko.model.Composition
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.inline.InlineElement

interface ListItem : LineExtension

class SimpleItem(val element: InlineElement) : ListItem {
	override fun <R> accept(visitor: LineVisitor<R>): R = visitor.visit(this)
	override fun renderedAs(format: EmbeddedCode.Format): String = when (format) {
		EmbeddedCode.Format.Markdown -> "- ${element.renderedAs(format)}"
		EmbeddedCode.Format.Tex -> "\\item ${element.renderedAs(format)}"
		else -> element.renderedAs(format)
	}
}

class IndexedItem(val element: InlineElement) : ListItem {
	override fun <R> accept(visitor: LineVisitor<R>): R = visitor.visit(this)
	override fun renderedAs(format: EmbeddedCode.Format): String = when (format) {
		EmbeddedCode.Format.Markdown -> "1. ${element.renderedAs(format)}"
		EmbeddedCode.Format.Tex -> "\\item ${element.renderedAs(format)}"
		else -> TODO()
	}
}

class DescriptionItem(
		val title: InlineElement,
		val element: InlineElement,
		val lineBreak: Boolean = true) : ListItem {
	override fun <R> accept(visitor: LineVisitor<R>): R = visitor.visit(this)
	override fun renderedAs(format: EmbeddedCode.Format): String = when (format) {
		EmbeddedCode.Format.Markdown -> {
			val title = title.renderedAs(format)
			val element = element.renderedAs(format)
			if (lineBreak)
				"- $title\n\n  $element\n"
			else
				"- $title $element"
		}
		EmbeddedCode.Format.Tex -> {
			val title = title.renderedAs(format)
			val element = element.renderedAs(format)
			if (lineBreak)
				"\\item[$title]\\mbox{}\\\\$element"
			else
				"\\item[$title]$element"
		}
		else -> TODO()
	}
}

interface ItemList : ListItem, Composition<ListItem> {
	val items: List<ListItem>
	override fun <R> accept(visitor: LineVisitor<R>): R = visitor.visit(this)
	override fun renderedAs(format: EmbeddedCode.Format): String = when (format) {
		EmbeddedCode.Format.Markdown -> {
			val items = items
			val suffix = items.lastOrNull().let {
				if (it is DescriptionItem && it.lineBreak) "\n"
				else "\n\n"
			}
			val listString = items.joinToString("\n") {
				val rendered = it.renderedAs(format)
				if (it is ItemList)
					rendered.prependIndent("\t")
				else
					rendered
			} + suffix
			listString
					.replace(Regex("\t+\n"), "\n") // remove redundant tabs
					.replace("\n\n\n", "\n")
		}
		EmbeddedCode.Format.Tex -> {    // TODO
			val items = items
			val leafItems = items.filterNot { it is ItemList }
			val envName = when (true) {
				leafItems.all { it is SimpleItem } -> "itemize"
				leafItems.all { it is IndexedItem } -> "enumerate"
				leafItems.all { it is DescriptionItem } -> "description"
				else -> throw IllegalStateException()
			}
			"${Begin(envName)}\n" +
					items.joinToString("\n") { it.renderedAs(format) } + "\n" +
					End(envName)
		}
		else -> TODO()
	}

	companion object {
		fun Iterable<ListItem>.toComposition(): ItemList {
			return ItemListData(this.toList())
		}
	}

	private data class ItemListData(override val items: List<ListItem>) : ItemList
}