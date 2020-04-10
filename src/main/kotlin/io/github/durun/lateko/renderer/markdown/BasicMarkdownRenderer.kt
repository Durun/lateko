package io.github.durun.lateko.renderer.markdown

import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.structure.StructureElement
import io.github.durun.lateko.renderer.common.ChangeSectionIdVisitor
import io.github.durun.lateko.renderer.common.DocumentRenderVisitor
import io.github.durun.lateko.renderer.common.StructureRenderVisitor
import io.github.durun.lateko.renderer.markdown.visitor.MarkdownStructureRenderVisitor

object BasicMarkdownRenderer : MarkdownRenderer {
	override fun render(document: StructureElement): String {
		return document
				.accept(ChangeSectionIdVisitor(document.context))
				.accept(MarkdownRenderVisitor())
	}

	private class MarkdownRenderVisitor : DocumentRenderVisitor,
			StructureRenderVisitor by MarkdownStructureRenderVisitor() {
		override val outputFormat = Format.Markdown
		override fun InlineElement.rendered(): String = this.renderedAs(outputFormat)
	}
}