package lateko.renderer.markdown.visitor

import lateko.command.markdown.Header
import lateko.model.Document
import lateko.model.inline.InlineElement
import lateko.model.line.LineElement
import lateko.model.structure.Chapter
import lateko.model.structure.Section
import lateko.model.structure.StructureElement
import lateko.renderer.markdown.MarkdownEscaper
import lateko.renderer.markdown.anchor

internal class MarkdownSectionsRenderVisitor(
		private val V: MarkdownInlineRenderVisitor,
		private val W: MarkdownStructureRenderVisitor) {
	private val InlineElement.rendered: String
		get() = this.accept(V)
	private val LineElement.rendered: String
		get() = this.accept(V)
	private val StructureElement.rendered: String
		get() = this.accept(W)

	private var sectionNestLevel = 1

	fun visit(document: Document): String {
		val docName = document.name?.let { MarkdownEscaper.escape(it) }
		return Header(level = 1, text = docName).toString() +
				document.content.rendered
	}

	fun visit(section: Section): String {
		sectionNestLevel++
		val content =
				"\n${section.anchor}\n\n" +
						Header(level = sectionNestLevel, text = section.name?.rendered).toString() +
						section.content.rendered
		sectionNestLevel--
		return content
	}

	fun visit(chapter: Chapter): String {
		sectionNestLevel++
		val content =
				"\n${chapter.anchor}\n\n" +
						Header(level = sectionNestLevel, text = chapter.name.rendered).toString() +
						chapter.content.rendered
		sectionNestLevel--
		return content
	}
}