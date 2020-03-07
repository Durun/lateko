package lateko.renderer.tex.visitor

import lateko.command.tex.Begin
import lateko.command.tex.End
import lateko.command.tex.SubSection
import lateko.command.tex.SubSubSection
import lateko.dsl.structure.IllegalNestError
import lateko.model.Document
import lateko.model.inline.InlineElement
import lateko.model.line.LineElement
import lateko.model.structure.Chapter
import lateko.model.structure.Section
import lateko.model.structure.StructureElement

internal class TexSectionsRenderVisitor(
		private val V: TexInlineRenderVisitor,
		private val W: TexStructureRenderVisitor) {
	private val InlineElement.rendered: String
		get() = this.accept(V)
	private val LineElement.rendered: String
		get() = this.accept(V)
	private val StructureElement.rendered: String
		get() = this.accept(W)

	private var sectionNestLevel = 0
	private var chapterNestLevel = 0

	fun visit(section: Section): String {
		assert(sectionNestLevel >= 0)
		sectionNestLevel++
		val sectionName = section.name?.rendered.orEmpty()
		val sectionCommand = when (sectionNestLevel) {
			1 -> lateko.command.tex.Section(sectionName)
			2 -> SubSection(sectionName)
			3 -> SubSubSection(sectionName)
			else -> throw IllegalNestError("Too many section nest.")
		}
		val content = "$sectionCommand\n${section.content.rendered}"
		sectionNestLevel--
		return content
	}

	fun visit(chapter: Chapter): String {
		assert(chapterNestLevel >= 0)
		chapterNestLevel++
		if (sectionNestLevel != 0) throw IllegalNestError("Chapter must not be in a section.")
		if (chapterNestLevel > 1) throw IllegalNestError("Too many chapter nest.")

		val chapterName = chapter.name.rendered
		val chapterCommand = lateko.command.tex.Chapter(chapterName)
		val content = "$chapterCommand\n${chapter.content.rendered}"
		chapterNestLevel--
		return content
	}

	fun visit(document: Document): String {
		return """${document.header.rendered}
			|${Begin("document")}
			|${document.content.rendered}
			|${End("document")}""".trimMargin()
	}
}