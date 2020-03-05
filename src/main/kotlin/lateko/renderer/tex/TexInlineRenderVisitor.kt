package lateko.renderer.tex

import lateko.element.EmbeddedCode
import lateko.element.UrlText
import lateko.visitor.InlineRenderVisitor


internal object TexInlineRenderVisitor : InlineRenderVisitor {
	override fun String.escape(): String = TexEscaper.escape(this)
	override fun EmbeddedCode.isEnabled(): Boolean = this.format == EmbeddedCode.Format.Tex

	override fun visit(urlText: UrlText): String {
		TODO("Not yet implemented")
	}
}


