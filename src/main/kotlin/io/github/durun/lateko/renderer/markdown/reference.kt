package io.github.durun.lateko.renderer.markdown

import io.github.durun.lateko.model.Labeled
import io.github.durun.lateko.target.markdown.Anchor

internal val Labeled.anchor get() = Anchor(this.label)