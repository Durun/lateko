package io.github.durun.lateko.renderer.markdown.visitor

import io.github.durun.lateko.command.markdown.Link
import io.github.durun.lateko.model.inline.UrlText
import io.github.durun.lateko.renderer.common.InlineRenderVisitor

internal interface MarkdownUrlRenderVisitor : InlineRenderVisitor {
	override fun visit(urlText: UrlText): String = Link(url = urlText.url, element = urlText.text.rendered).toString()
}