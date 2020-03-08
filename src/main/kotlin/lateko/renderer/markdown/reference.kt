package lateko.renderer.markdown

import lateko.command.markdown.Anchor
import lateko.model.structure.StructureElement

internal val StructureElement.anchor get() = Anchor(this.id)