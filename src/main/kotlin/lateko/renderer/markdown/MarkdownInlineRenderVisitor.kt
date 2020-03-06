package lateko.renderer.markdown

import lateko.element.EmbeddedCode
import lateko.element.UrlText
import lateko.visitor.InlineRenderVisitor

internal object MarkdownInlineRenderVisitor : InlineRenderVisitor {
	override fun String.escape(): String = MarkdownEscaper.escape(this)
	override fun EmbeddedCode.isEnabled(): Boolean = this.format == EmbeddedCode.Format.Markdown

	override fun visit(urlText: UrlText): String {
		return "[${urlText.text.rendered}](${urlText.url})"
	}
}
