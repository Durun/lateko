package lateko.renderer

import lateko.element.*
import lateko.visitor.DocumentVisitor
import lateko.visitor.InlineVisitor

private val InlineElement.rendered: String
	get() {
		return this.accept(MarkdownInlineRenderVisitor)
	}

object MarkdownInlineRenderVisitor : InlineVisitor<String>, MarkdownRenderer {
	override fun visit(text: Text): String {
		return text.text
	}

	override fun visit(urlText: UrlText): String {
		return "[${urlText.text.rendered}](${urlText.url})"
	}

	override fun visit(composition: InlineComposition): String {
		return composition.children.joinToString("\n") { it.rendered }
	}
}

open class MarkdownRenderVisitor : DocumentVisitor<String>, MarkdownRenderer
		, InlineVisitor<String> by MarkdownInlineRenderVisitor {
	private var sectionNestLevel = 1

	private val StructureElement.rendered: String
		get() {
			return this.accept(this@MarkdownRenderVisitor)
		}

	override fun visit(paragraph: Paragraph): String {
		return paragraph.content.rendered + "\n\n"
	}

	override fun visit(document: Document): String {
		val title = document.name?.let { "# ${it.trim()}\n" }.orEmpty()
		return title + document.content.rendered
	}

	override fun visit(section: Section): String {
		sectionNestLevel++
		val sectionName = section.name?.rendered ?: "　"
		val content = "#".repeat(sectionNestLevel) + " " + sectionName.trim() + "\n" +
				section.content.rendered
		sectionNestLevel--
		return content
	}

	override fun visit(chapter: Chapter): String {
		sectionNestLevel++
		val content = "#".repeat(sectionNestLevel) + " " + chapter.name.rendered.trim() + "\n" +
				chapter.content.rendered
		sectionNestLevel--
		return content
	}

	override fun visit(composition: StructureComposition): String {
		return composition.children.joinToString("") { it.rendered }
	}
}