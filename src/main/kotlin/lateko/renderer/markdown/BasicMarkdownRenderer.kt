package lateko.renderer.markdown

import lateko.model.Document

object BasicMarkdownRenderer : MarkdownRenderer {
	override fun render(document: Document): String {
		val visitor = MarkdownRenderVisitor()
		return document.accept(visitor)
	}
}