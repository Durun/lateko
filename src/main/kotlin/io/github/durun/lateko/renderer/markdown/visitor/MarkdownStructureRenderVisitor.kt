package io.github.durun.lateko.renderer.markdown.visitor

import io.github.durun.lateko.command.markdown.Header
import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.line.LineElement
import io.github.durun.lateko.model.structure.*
import io.github.durun.lateko.renderer.common.StructureRenderVisitor
import io.github.durun.lateko.renderer.markdown.MarkdownEscaper
import io.github.durun.lateko.renderer.markdown.anchor

internal class MarkdownStructureRenderVisitor : StructureRenderVisitor {
	override val outputFormat = Format.Markdown
	private val InlineElement.rendered: String get() = this.renderedAs(outputFormat)
	private val LineElement.rendered: String get() = this.renderedAs(outputFormat)
	private val StructureElement.rendered: String get() = this.accept(this@MarkdownStructureRenderVisitor)
	override fun visit(structure: Structure): String = structure.element.rendered
	override fun visit(structure: StructureExtension): String = structure.renderedAs(outputFormat)

	private var sectionNestLevel = 1

	override fun visit(document: Document): String {
		val docName = document.name?.let { MarkdownEscaper.escape(it) }
		return Header(level = 1, text = docName).toString() +
				document.content.rendered
	}

	override fun visit(section: Section): String {
		sectionNestLevel++
		val content =
				"\n${section.anchor}\n\n" +
						Header(level = sectionNestLevel, text = section.name?.rendered).toString() +
						section.content.rendered
		sectionNestLevel--
		return content
	}

	override fun visit(chapter: Chapter): String {
		sectionNestLevel++
		val content =
				"\n${chapter.anchor}\n\n" +
						Header(level = sectionNestLevel, text = chapter.name.rendered).toString() +
						chapter.content.rendered
		sectionNestLevel--
		return content
	}
}