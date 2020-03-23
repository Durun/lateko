package io.github.durun.lateko.renderer.markdown.visitor

import io.github.durun.lateko.command.markdown.Header
import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.structure.Chapter
import io.github.durun.lateko.model.structure.Section
import io.github.durun.lateko.model.structure.StructureElement
import io.github.durun.lateko.renderer.markdown.MarkdownEscaper
import io.github.durun.lateko.renderer.markdown.anchor

internal class MarkdownSectionsRenderVisitor(
		private val structureRenderVisitor: MarkdownStructureRenderVisitor) {
	private val InlineElement.rendered: String
		get() = this.renderedAs(EmbeddedCode.Format.Markdown)
	private val StructureElement.rendered: String
		get() = this.accept(structureRenderVisitor)

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