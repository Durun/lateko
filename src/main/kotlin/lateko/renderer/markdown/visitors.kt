package lateko.renderer.markdown

import lateko.element.*
import lateko.visitor.DocumentRenderVisitor
import lateko.visitor.InlineRenderVisitor

private val InlineElement.rendered: String
	get() {
		return this.accept(MarkdownInlineRenderVisitor)
	}
private val LineElement.renderedLine: String
	get() {
		return this.accept(MarkdownInlineRenderVisitor)
	}

internal object MarkdownInlineRenderVisitor : InlineRenderVisitor {
	override fun EmbeddedCode.isEnabled(): Boolean = this.format == EmbeddedCode.Format.Markdown

	override fun visit(text: Text): String {
		return MarkdownEscaper.escape(text.text)
	}

	override fun visit(urlText: UrlText): String {
		return "[${urlText.text.rendered}](${urlText.url})"
	}

	override fun visit(composition: InlineComposition): String {
		val childrenStr = composition.children.map { it.accept(this) }
		return childrenStr.joinToString("")
	}

	override fun visit(code: EmbeddedCode): String {
		val str = code.takeIf { it.isEnabled() }?.code
		return str.orEmpty()
	}

	override fun visit(lines: LineComposition): String {
		return lines.children.joinToString("") { it.accept(this) }
	}

	override fun visit(line: Line): String {
		val lineStr = line.element.accept(this) + "\n"
		return lineStr.takeUnless {
			line.element is EmbeddedCode && !line.element.isEnabled()
		}.orEmpty()
	}
}


internal class MarkdownRenderVisitor : DocumentRenderVisitor
		, InlineRenderVisitor by MarkdownInlineRenderVisitor {
	private var sectionNestLevel = 1

	override fun visit(paragraph: Paragraph): String {
		return paragraph.content.renderedLine + "\n"
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

	override fun visit(structure: Structure): String {
		return structure.element.renderedLine
	}
}