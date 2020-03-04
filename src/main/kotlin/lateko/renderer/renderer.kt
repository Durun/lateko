package lateko.renderer

import lateko.element.Document

interface Renderer {
	fun render(document: Document): String
}

interface MarkdownRenderer : Renderer

interface TexRenderer : Renderer