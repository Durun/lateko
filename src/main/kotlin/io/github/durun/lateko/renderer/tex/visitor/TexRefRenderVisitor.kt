package io.github.durun.lateko.renderer.tex.visitor

import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.inline.Reference
import io.github.durun.lateko.renderer.common.InlineRenderVisitor

internal interface TexRefRenderVisitor : InlineRenderVisitor {
	override fun visit(ref: Reference): String {
		return ref.renderedAs(EmbeddedCode.Format.Tex).orEmpty()
	}
}