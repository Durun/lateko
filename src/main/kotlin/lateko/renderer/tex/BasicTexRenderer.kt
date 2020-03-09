package lateko.renderer.tex

import lateko.model.Document
import lateko.model.inline.EmbeddedCode
import lateko.renderer.common.ChangeSectionIdVisitor
import lateko.renderer.common.DocumentRenderVisitor
import lateko.renderer.common.InlineRenderVisitor
import lateko.renderer.common.StructureRenderVisitor
import lateko.renderer.tex.visitor.TexInlineRenderVisitor
import lateko.renderer.tex.visitor.TexLineRenderVisitor
import lateko.renderer.tex.visitor.TexStructureRenderVisitor

object BasicTexRenderer : TexRenderer {
	override fun render(document: Document): String {
		return document
				.accept(ChangeSectionIdVisitor())
				.accept(TexRenderVisitor())
	}

	private class TexRenderVisitor : DocumentRenderVisitor,
			TexInlineRenderVisitor,
			TexLineRenderVisitor,
			StructureRenderVisitor by TexStructureRenderVisitor(TexInlineRenderVisitorObj, TexLineRenderVisitorObj) {

		private object TexInlineRenderVisitorObj : TexInlineRenderVisitor {
			override fun EmbeddedCode.isEnabled(): Boolean = isTex()
		}

		private object TexLineRenderVisitorObj : TexLineRenderVisitor {
			override val inlineRenderVisitor: InlineRenderVisitor = TexInlineRenderVisitorObj

			override fun EmbeddedCode.isEnabled(): Boolean = isTex()
		}

		override val inlineRenderVisitor: InlineRenderVisitor = TexInlineRenderVisitorObj
		override fun EmbeddedCode.isEnabled(): Boolean = isTex()
	}
}

private fun EmbeddedCode.isTex() = this.format == EmbeddedCode.Format.Tex