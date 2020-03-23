package io.github.durun.lateko.model.line

interface LineExtension : LineElement {
	override fun <R> accept(visitor: LineVisitor<R>): R = visitor.visit(this)
}