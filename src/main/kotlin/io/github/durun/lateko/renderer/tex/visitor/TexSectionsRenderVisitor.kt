package io.github.durun.lateko.renderer.tex.visitor

import io.github.durun.lateko.command.tex.*
import io.github.durun.lateko.dsl.structure.IllegalNestError
import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.line.LineElement
import io.github.durun.lateko.model.structure.Chapter
import io.github.durun.lateko.model.structure.Section
import io.github.durun.lateko.model.structure.StructureElement

internal class TexSectionsRenderVisitor {
	private val InlineElement.rendered: String
		get() = this.renderedAs(EmbeddedCode.Format.Tex)
	private val LineElement.rendered: String
		get() = this.renderedAs(EmbeddedCode.Format.Tex)
	private val StructureElement.rendered: String
		get() = this.renderedAs(EmbeddedCode.Format.Tex)

	private var sectionNestLevel = 0
	private var chapterNestLevel = 0

	fun visit(section: Section): String {
		assert(sectionNestLevel >= 0)
		sectionNestLevel++
		val sectionName = section.name?.rendered.orEmpty()
		val sectionCommand = when (sectionNestLevel) {
			1 -> Section(sectionName)
			2 -> SubSection(sectionName)
			3 -> SubSubSection(sectionName)
			else -> throw IllegalNestError("Too many section nest.")
		}
		val labelCommand = Label(section.id)
		val content = "$sectionCommand$labelCommand\n${section.content.rendered}"
		sectionNestLevel--
		return content
	}

	fun visit(chapter: Chapter): String {
		assert(chapterNestLevel >= 0)
		chapterNestLevel++
		if (sectionNestLevel != 0) throw IllegalNestError("Chapter must not be in a section.")
		if (chapterNestLevel > 1) throw IllegalNestError("Too many chapter nest.")

		val chapterName = chapter.name.rendered
		val chapterCommand = Chapter(chapterName)
		val labelCommand = Label(chapter.id)
		val content = "$chapterCommand$labelCommand\n${chapter.content.rendered}"
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