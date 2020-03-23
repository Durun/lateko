package io.github.durun.lateko.renderer.markdown.visitor

import io.github.durun.lateko.command.markdown.AnchorLink
import io.github.durun.lateko.model.inline.Reference
import io.github.durun.lateko.renderer.common.InlineRenderVisitor

internal interface MarkdownRefRenderVisitor : InlineRenderVisitor {
	override fun visit(ref: Reference): String {
		return AnchorLink(url = "#${ref.id}", element = ref.id).toString()
	}
}