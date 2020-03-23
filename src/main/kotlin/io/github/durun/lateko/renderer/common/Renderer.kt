package io.github.durun.lateko.renderer.common

import io.github.durun.lateko.model.Document

interface Renderer {
	fun render(document: Document): String
}
