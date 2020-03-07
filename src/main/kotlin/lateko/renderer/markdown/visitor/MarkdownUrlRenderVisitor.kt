package lateko.renderer.markdown.visitor

import lateko.command.markdown.Link
import lateko.model.inline.UrlText
import lateko.renderer.common.InlineRenderVisitor

internal interface MarkdownUrlRenderVisitor : InlineRenderVisitor {
	override fun visit(urlText: UrlText): String = Link(url = urlText.url, element = urlText.text.rendered).toString()
}