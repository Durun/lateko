package io.github.durun.lateko.model.inline

import io.github.durun.lateko.dsl.inline.text

data class UrlText(
		val url: String,
		val text: InlineElement = url.text
) : InlineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
}