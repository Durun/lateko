package io.github.durun.lateko.renderer.common

import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.inline.*

interface InlineRenderVisitor : InlineVisitor<String> {
	val outputFormat: Format
	fun InlineElement.rendered(): String = this.renderedAs(outputFormat)


	override fun visit(composition: InlineComposition): String {
		return composition.children.joinToString("") { it.rendered() }
	}

	override fun visit(code: EmbeddedCode): String = code.rendered()
	override fun visit(element: InlineExtension): String = element.rendered()
	override fun visit(ref: Reference): String = ref.rendered()
}