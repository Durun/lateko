package io.github.durun.lateko.model.inline

import io.github.durun.lateko.command.markdown.AnchorLink
import io.github.durun.lateko.command.tex.Ref
import io.github.durun.lateko.model.structure.StructureElement

class Reference(val element: StructureElement) : InlineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
	val id: String get() = element.id

	override fun renderedAs(format: EmbeddedCode.Format): String = when (format) {
		EmbeddedCode.Format.Markdown -> AnchorLink(url = "#$id", element = id).toString()
		EmbeddedCode.Format.Tex -> Ref(id).toString()
		else -> "ref($id)"
	}
}