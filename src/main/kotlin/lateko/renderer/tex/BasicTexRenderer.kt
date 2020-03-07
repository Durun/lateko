package lateko.renderer.tex

import lateko.command.tex.*
import lateko.dsl.structure.IllegalNestError
import lateko.model.Document
import lateko.model.inline.EmbeddedCode
import lateko.model.inline.InlineElement
import lateko.model.line.LineElement
import lateko.model.structure.Chapter
import lateko.model.structure.Paragraph
import lateko.model.structure.Section
import lateko.model.structure.Structure
import lateko.renderer.common.DocumentRenderVisitor
import lateko.renderer.common.InlineRenderVisitor
import lateko.renderer.common.StructureRenderVisitor

object BasicTexRenderer : TexRenderer {
	override fun render(document: Document): String {
		return TexRenderVisitor().visit(document)
	}

	private class TexRenderVisitor : DocumentRenderVisitor,
			TexInlineRenderVisitor,
			StructureRenderVisitor by TexStructureRenderVisitor()


	private interface TexInlineRenderVisitor :
			TexInlineRenderVisitorCore,
			TexUrlRenderVisitor

	private interface TexInlineRenderVisitorCore : InlineRenderVisitor {
		override fun String.escape(): String = TexEscaper.escape(this)
		override fun EmbeddedCode.isEnabled(): Boolean = this.format == EmbeddedCode.Format.Tex
	}


	private class TexStructureRenderVisitor :
			StructureRenderVisitor by TexStructureRenderVisitorCore()

	private class TexStructureRenderVisitorCore : StructureRenderVisitor {
		private object V : TexInlineRenderVisitor

		private val InlineElement.rendered: String
			get() = this.accept(V)
		private val LineElement.rendered: String
			get() = this.accept(V)

		private var sectionNestLevel = 0
		private var chapterNestLevel = 0

		override fun visit(paragraph: Paragraph): String = paragraph.content.rendered + "\n"

		override fun visit(section: Section): String {
			assert(sectionNestLevel >= 0)
			sectionNestLevel++
			val sectionName = section.name?.rendered.orEmpty()
			val sectionCommand = when (sectionNestLevel) {
				1 -> Section(sectionName)
				2 -> SubSection(sectionName)
				3 -> SubSubSection(sectionName)
				else -> throw IllegalNestError("Too many section nest.")
			}
			val content = "$sectionCommand\n${section.content.rendered}"
			sectionNestLevel--
			return content
		}

		override fun visit(chapter: Chapter): String {
			assert(chapterNestLevel >= 0)
			chapterNestLevel++
			if (sectionNestLevel != 0) throw IllegalNestError("Chapter must not be in a section.")
			if (chapterNestLevel > 1) throw IllegalNestError("Too many chapter nest.")

			val chapterName = chapter.name.rendered
			val chapterCommand = Chapter(chapterName)
			val content = "$chapterCommand\n${chapter.content.rendered}"
			chapterNestLevel--
			return content
		}

		override fun visit(document: Document): String {
			return """${document.header.rendered}
			|${Begin("document")}
			|${document.content.rendered}
			|${End("document")}""".trimMargin()
		}

		override fun visit(structure: Structure): String = structure.element.rendered
	}
}