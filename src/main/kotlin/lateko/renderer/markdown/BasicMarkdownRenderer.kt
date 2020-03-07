package lateko.renderer.markdown

import lateko.command.markdown.Header
import lateko.command.markdown.Link
import lateko.model.Document
import lateko.model.inline.EmbeddedCode
import lateko.model.inline.InlineElement
import lateko.model.inline.UrlText
import lateko.model.line.LineElement
import lateko.model.structure.Chapter
import lateko.model.structure.Paragraph
import lateko.model.structure.Section
import lateko.model.structure.Structure
import lateko.renderer.common.DocumentRenderVisitor
import lateko.renderer.common.InlineRenderVisitor
import lateko.renderer.common.StructureRenderVisitor

object BasicMarkdownRenderer : MarkdownRenderer {
	override fun render(document: Document): String {
		val visitor = MarkdownRenderVisitor()
		return document.accept(visitor)
	}

	private class MarkdownRenderVisitor : DocumentRenderVisitor,
			MarkdownInlineRenderVisitor,
			StructureRenderVisitor by MarkdownStructureRenderVisitor()

	private interface MarkdownInlineRenderVisitor : MarkdownInlineRenderVisitorCore, MarkdownUrlRenderVisitor

	private interface MarkdownInlineRenderVisitorCore : InlineRenderVisitor {
		override fun String.escape(): String = MarkdownEscaper.escape(this)
		override fun EmbeddedCode.isEnabled(): Boolean = this.format == EmbeddedCode.Format.Markdown
	}

	private interface MarkdownUrlRenderVisitor : InlineRenderVisitor {
		override fun visit(urlText: UrlText): String = Link(url = urlText.url, element = urlText.text.rendered).toString()
	}

	private class MarkdownStructureRenderVisitor : StructureRenderVisitor {
		private object V : MarkdownInlineRenderVisitor

		private val InlineElement.rendered: String
			get() = this.accept(V)
		private val LineElement.rendered: String
			get() = this.accept(V)

		private var sectionNestLevel = 1

		override fun visit(paragraph: Paragraph): String = paragraph.content.rendered + "\n"

		override fun visit(document: Document): String {
			return Header(level = 1, text = document.name).toString() +
					document.content.rendered
		}

		override fun visit(section: Section): String {
			sectionNestLevel++
			val content =
					Header(level = sectionNestLevel, text = section.name?.rendered).toString() +
							section.content.rendered
			sectionNestLevel--
			return content
		}

		override fun visit(chapter: Chapter): String {
			sectionNestLevel++
			val content =
					Header(level = sectionNestLevel, text = chapter.name.rendered).toString() +
							chapter.content.rendered
			sectionNestLevel--
			return content
		}

		override fun visit(structure: Structure): String = structure.element.rendered
	}
}