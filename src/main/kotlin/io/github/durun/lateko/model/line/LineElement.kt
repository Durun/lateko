package io.github.durun.lateko.model.line

import io.github.durun.lateko.model.Element
import io.github.durun.lateko.model.inline.EmbeddedCode

interface LineElement : Element {
	fun <R> accept(visitor: LineVisitor<R>): R
	fun renderedAs(format: EmbeddedCode.Format): String
}