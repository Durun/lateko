package io.github.durun.lateko.model.inline

import io.github.durun.lateko.model.Element

interface InlineElement : Element {
	fun <R> accept(visitor: InlineVisitor<R>): R
}