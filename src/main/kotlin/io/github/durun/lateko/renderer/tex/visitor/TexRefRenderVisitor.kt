package io.github.durun.lateko.renderer.tex.visitor

import io.github.durun.lateko.command.tex.Ref
import io.github.durun.lateko.model.inline.Reference
import io.github.durun.lateko.renderer.common.InlineRenderVisitor

internal interface TexRefRenderVisitor : InlineRenderVisitor {
	override fun visit(ref: Reference): String {
		return Ref(ref.id).toString()
	}
}