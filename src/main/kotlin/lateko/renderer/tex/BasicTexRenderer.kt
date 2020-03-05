package lateko.renderer.tex

import lateko.element.Document
import lateko.renderer.TexRenderer

object BasicTexRenderer: TexRenderer {
	override fun render(document: Document): String {
		return TexRenderVisitor().visit(document)
	}
}