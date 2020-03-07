package lateko.renderer.tex

import lateko.command.tex.SimpleTexCommand
import lateko.model.inline.UrlText
import lateko.renderer.common.InlineRenderVisitor


interface TexUrlRenderVisitor : InlineRenderVisitor {
	override fun visit(urlText: UrlText): String {
		val text = urlText.text.rendered
		val url = urlText.url
		return if (text == url)
			SimpleTexCommand("url", arg = url).toString()
		else
			SimpleTexCommand("href", args = listOf(url, text)).toString()
	}
}