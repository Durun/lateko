package io.github.durun.lateko.model.inline

import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.structure.StructureElement
import io.github.durun.lateko.target.markdown.AnchorLink
import io.github.durun.lateko.target.tex.Ref

open class Reference(val label: String) : InlineElement {
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)

	override fun renderedAs(format: Format): String = when (format) {
		Format.Markdown -> AnchorLink(url = "#$label", element = label).toString()
		Format.Tex -> Ref(label).toString()
		else -> "ref($label)"
	}
}

class StructureReference(val element: StructureElement) : Reference(element.id)