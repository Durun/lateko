package io.github.durun.lateko.renderer.common

import io.github.durun.lateko.model.structure.StructureElement

interface Renderer {
	fun render(document: StructureElement): String
}
