package lateko.renderer.markdown

import lateko.element.Document
import lateko.renderer.MarkdownRenderer

object BasicMarkdownRenderer : MarkdownRenderer {
	override fun render(document: Document): String {
		val visitor = MarkdownRenderVisitor()
		return document.accept(visitor)
	}
}