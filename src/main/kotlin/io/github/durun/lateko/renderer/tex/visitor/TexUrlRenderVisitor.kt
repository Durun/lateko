package io.github.durun.lateko.renderer.tex.visitor

import io.github.durun.lateko.command.tex.SimpleTexCommand
import io.github.durun.lateko.model.inline.UrlText
import io.github.durun.lateko.renderer.common.InlineRenderVisitor


internal interface TexUrlRenderVisitor : InlineRenderVisitor {
	override fun visit(urlText: UrlText): String {
		val text = urlText.text.rendered
		val url = urlText.url
		return if (text == url)
			SimpleTexCommand("url", arg = url).toString()
		else
			SimpleTexCommand("href", args = listOf(url, text)).toString()
	}
}