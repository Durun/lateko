package lateko.renderer

import lateko.model.Document

interface Renderer {
	fun render(document: Document): String
}

interface MarkdownRenderer : Renderer

interface TexRenderer : Renderer