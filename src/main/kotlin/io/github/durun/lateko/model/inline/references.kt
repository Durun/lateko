package io.github.durun.lateko.model.inline

import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.Labeled
import io.github.durun.lateko.model.structure.StructureElement
import io.github.durun.lateko.target.markdown.AnchorLink
import io.github.durun.lateko.target.tex.Ref

interface Reference : InlineElement {
	val label: String
	override fun <R> accept(visitor: InlineVisitor<R>): R = visitor.visit(this)

	override fun renderedAs(format: Format): String = when (format) {
		Format.Markdown -> AnchorLink(url = "#$label", element = label).toString()
		Format.Tex -> Ref(label).toString()
		else -> "ref($label)"
	}
}

class SimpleReference(override val label: String) : Reference
class LazyReference(val element: Labeled) : Reference {
	override val label: String get() = element.label
}