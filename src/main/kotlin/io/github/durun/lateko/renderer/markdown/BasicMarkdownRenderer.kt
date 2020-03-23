package io.github.durun.lateko.renderer.markdown

import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.renderer.common.ChangeSectionIdVisitor
import io.github.durun.lateko.renderer.common.DocumentRenderVisitor
import io.github.durun.lateko.renderer.common.StructureRenderVisitor
import io.github.durun.lateko.renderer.markdown.visitor.MarkdownStructureRenderVisitor

object BasicMarkdownRenderer : MarkdownRenderer {
	override fun render(document: Document): String {
		return document
				.accept(ChangeSectionIdVisitor())
				.accept(MarkdownRenderVisitor())
	}

	private class MarkdownRenderVisitor : DocumentRenderVisitor,
			StructureRenderVisitor by MarkdownStructureRenderVisitor() {

		override val InlineElement.rendered: String
			get() = this.renderedAs(outputFormat())

		override fun outputFormat() = EmbeddedCode.Format.Markdown
	}
}