package lateko.renderer.tex

import lateko.element.EmbeddedCode
import lateko.element.Text
import lateko.element.UrlText
import lateko.visitor.InlineRenderVisitor


internal object TexInlineRenderVisitor : InlineRenderVisitor {
	override fun EmbeddedCode.isEnabled(): Boolean = this.format == EmbeddedCode.Format.Tex

	override fun visit(text: Text): String = TexEscaper.escape(text.text)

	override fun visit(urlText: UrlText): String {
		TODO("Not yet implemented")
	}
}


