package io.github.durun.lateko.model.inline

import io.github.durun.lateko.model.Element
import io.github.durun.lateko.model.Format

interface InlineElement : Element {
	fun <R> accept(visitor: InlineVisitor<R>): R
	fun renderedAs(format: Format): String
}