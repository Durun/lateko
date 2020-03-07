package lateko.renderer.markdown

import lateko.model.Document
import lateko.model.inline.EmbeddedCode
import lateko.model.inline.UrlText
import lateko.model.structure.Chapter
import lateko.model.structure.Paragraph
import lateko.model.structure.Section
import lateko.model.structure.Structure
import lateko.renderer.common.DocumentRenderVisitor
import lateko.renderer.common.InlineRenderVisitor

object BasicMarkdownRenderer : MarkdownRenderer {
	override fun render(document: Document): String {
		val visitor = MarkdownRenderVisitor()
		return document.accept(visitor)
	}

	private object MarkdownInlineRenderVisitor : InlineRenderVisitor {
		override fun String.escape(): String = MarkdownEscaper.escape(this)
		override fun EmbeddedCode.isEnabled(): Boolean = this.format == EmbeddedCode.Format.Markdown

		override fun visit(urlText: UrlText): String {
			return "[${urlText.text.rendered}](${urlText.url})"
		}
	}

	private class MarkdownRenderVisitor : DocumentRenderVisitor
			, InlineRenderVisitor by MarkdownInlineRenderVisitor {
		private var sectionNestLevel = 1

		override fun visit(paragraph: Paragraph): String = paragraph.content.rendered + "\n"

		override fun visit(document: Document): String {
			val title = document.name?.let { "# ${it.trim()}\n" }.orEmpty()
			return title + document.content.rendered
		}

		override fun visit(section: Section): String {
			assert(sectionNestLevel >= 1)
			sectionNestLevel++
			val sectionName = section.name?.rendered ?: "　"
			val content = "#".repeat(sectionNestLevel) + " " + sectionName.trim() + "\n" +
					section.content.rendered
			sectionNestLevel--
			return content
		}

		override fun visit(chapter: Chapter): String {
			assert(sectionNestLevel >= 1)
			sectionNestLevel++
			val content = "#".repeat(sectionNestLevel) + " " + chapter.name.rendered.trim() + "\n" +
					chapter.content.rendered
			sectionNestLevel--
			return content
		}

		override fun visit(structure: Structure): String = structure.element.rendered
	}
}