package lateko.renderer.markdown.visitor

import lateko.command.markdown.Link
import lateko.model.inline.Reference
import lateko.renderer.common.InlineRenderVisitor

internal interface MarkdownRefRenderVisitor : InlineRenderVisitor {
	override fun visit(ref: Reference): String {
		return Link(url = "#${ref.id}", element = ref.id).toString()
	}
}