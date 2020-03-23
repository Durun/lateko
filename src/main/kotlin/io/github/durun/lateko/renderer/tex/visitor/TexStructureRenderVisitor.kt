package io.github.durun.lateko.renderer.tex.visitor

import io.github.durun.lateko.command.tex.*
import io.github.durun.lateko.dsl.structure.IllegalNestError
import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.line.LineElement
import io.github.durun.lateko.model.structure.*
import io.github.durun.lateko.model.structure.Chapter
import io.github.durun.lateko.model.structure.Section
import io.github.durun.lateko.renderer.common.StructureRenderVisitor

internal class TexStructureRenderVisitor : StructureRenderVisitor {
	override val outputFormat = Format.Tex
	private val InlineElement.rendered: String get() = this.renderedAs(outputFormat)
	private val LineElement.rendered: String get() = this.renderedAs(outputFormat)
	private val StructureElement.rendered: String get() = this.accept(this@TexStructureRenderVisitor)
	override fun visit(structure: Structure): String = structure.element.rendered
	override fun visit(structure: StructureExtension): String = structure.renderedAs(outputFormat)

	private var sectionNestLevel = 0
	private var chapterNestLevel = 0

	override fun visit(section: Section): String {
		assert(sectionNestLevel >= 0)
		sectionNestLevel++
		val sectionName = section.name?.rendered.orEmpty()
		val sectionCommand = when (sectionNestLevel) {
			1 -> io.github.durun.lateko.command.tex.Section(sectionName)
			2 -> SubSection(sectionName)
			3 -> SubSubSection(sectionName)
			else -> throw IllegalNestError("Too many section nest.")
		}
		val labelCommand = Label(section.id)
		val content = "$sectionCommand$labelCommand\n${section.content.rendered}"
		sectionNestLevel--
		return content
	}

	override fun visit(chapter: Chapter): String {
		assert(chapterNestLevel >= 0)
		chapterNestLevel++
		if (sectionNestLevel != 0) throw IllegalNestError("Chapter must not be in a section.")
		if (chapterNestLevel > 1) throw IllegalNestError("Too many chapter nest.")

		val chapterName = chapter.name.rendered
		val chapterCommand = io.github.durun.lateko.command.tex.Chapter(chapterName)
		val labelCommand = Label(chapter.id)
		val content = "$chapterCommand$labelCommand\n${chapter.content.rendered}"
		chapterNestLevel--
		return content
	}

	override fun visit(document: Document): String {
		return """${document.header.rendered}
			|${Begin("document")}
			|${document.content.rendered}
			|${End("document")}""".trimMargin()
	}
}