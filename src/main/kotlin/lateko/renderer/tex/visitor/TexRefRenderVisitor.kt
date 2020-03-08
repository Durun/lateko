package lateko.renderer.tex.visitor

import lateko.command.tex.Ref
import lateko.model.inline.Reference
import lateko.renderer.common.InlineRenderVisitor

internal interface TexRefRenderVisitor : InlineRenderVisitor {
	override fun visit(ref: Reference): String {
		return Ref(ref.id).toString()
	}
}