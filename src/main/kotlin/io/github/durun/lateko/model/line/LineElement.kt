package io.github.durun.lateko.model.line

import io.github.durun.lateko.model.Element

interface LineElement : Element {
	fun <R> accept(visitor: LineVisitor<R>): R
}