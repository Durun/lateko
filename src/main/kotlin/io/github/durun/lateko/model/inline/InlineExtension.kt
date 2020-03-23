package io.github.durun.lateko.model.inline

interface InlineExtension : InlineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
}