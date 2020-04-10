package io.github.durun.lateko.model.inline

import io.github.durun.lateko.target.markdown.AnchorLink
import io.github.durun.lateko.target.tex.Ref
import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.structure.StructureElement

class Reference(val element: StructureElement) : InlineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)
	val id: String get() = element.id

	override fun renderedAs(format: Format): String = when (format) {
		Format.Markdown -> AnchorLink(url = "#$id", element = id).toString()
		Format.Tex -> Ref(id).toString()
		else -> "ref($id)"
	}
}