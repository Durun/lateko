package lateko.renderer.common

import lateko.model.Document

interface Renderer {
	fun render(document: Document): String
}
