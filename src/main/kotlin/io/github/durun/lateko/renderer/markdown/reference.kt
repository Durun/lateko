package io.github.durun.lateko.renderer.markdown

import io.github.durun.lateko.target.markdown.Anchor
import io.github.durun.lateko.model.structure.StructureElement

internal val StructureElement.anchor get() = Anchor(this.id)