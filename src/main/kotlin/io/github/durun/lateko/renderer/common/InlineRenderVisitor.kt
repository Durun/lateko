package io.github.durun.lateko.renderer.common

import io.github.durun.lateko.model.inline.*

interface InlineRenderVisitor : InlineVisitor<String> {
	val InlineElement.rendered: String
		get() = this.accept(this@InlineRenderVisitor)

	fun String.escape(): String
	fun outputFormat(): EmbeddedCode.Format

	override fun visit(composition: InlineComposition): String {
		return composition.children.joinToString("") { it.rendered }
	}

	override fun visit(text: Text): String = text.text.escape()
	override fun visit(code: EmbeddedCode): String = code.takeIf { it.format == outputFormat() }?.code.orEmpty()
	override fun visit(element: InlineExtension): String = element.renderedAs(format = outputFormat())
}