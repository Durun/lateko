package lateko.renderer.tex

import lateko.command.TexCommand
import lateko.element.EmbeddedCode
import lateko.element.UrlText
import lateko.visitor.InlineRenderVisitor


internal object TexInlineRenderVisitor : InlineRenderVisitor {
	override fun String.escape(): String = TexEscaper.escape(this)
	override fun EmbeddedCode.isEnabled(): Boolean = this.format == EmbeddedCode.Format.Tex

	override fun visit(urlText: UrlText): String {
		val text = urlText.text.rendered
		val url = urlText.url.toString()
		return if (text == url)
			TexCommand("url", arg = url).toString()
		else
			TexCommand("href", args = listOf(url, text)).toString()
	}
}


