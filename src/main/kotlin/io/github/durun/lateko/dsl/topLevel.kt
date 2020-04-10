package io.github.durun.lateko.dsl

import io.github.durun.lateko.dsl.command.makeTitle
import io.github.durun.lateko.dsl.header.TexHeaderScope
import io.github.durun.lateko.dsl.inline.text
import io.github.durun.lateko.dsl.structure.ChapterScope
import io.github.durun.lateko.dsl.structure.ChapterScope.Companion.build
import io.github.durun.lateko.dsl.structure.DocumentScope
import io.github.durun.lateko.dsl.structure.ParagraphScope
import io.github.durun.lateko.dsl.structure.ParagraphScope.Companion.build
import io.github.durun.lateko.dsl.structure.SectionScope
import io.github.durun.lateko.dsl.structure.SectionScope.Companion.build
import io.github.durun.lateko.extension.Paragraph
import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.inline.Reference
import io.github.durun.lateko.model.structure.Chapter
import io.github.durun.lateko.model.structure.Section
import io.github.durun.lateko.model.structure.StructureElement

fun paragraphOf(content: ParagraphScope.() -> Unit): Paragraph = Paragraph(content.build())

fun sectionOf(title: InlineElement? = null, content: SectionScope.() -> Unit): Section = Section(name = title, content = content.build())
fun sectionOf(title: String, content: SectionScope.() -> Unit): Section = sectionOf(title = title.text, content = content)

fun chapterOf(title: InlineElement, content: ChapterScope.() -> Unit): Chapter = Chapter(name = title, content = content.build())
fun chapterOf(title: String, content: ChapterScope.() -> Unit): Chapter = chapterOf(title = title.text, content = content)


fun section(title: String, content: SectionScope.() -> Unit): Section = sectionOf(title = title, content = content)
fun chapter(title: String, content: ChapterScope.() -> Unit): Chapter = chapterOf(title = title, content = content)
fun document(name: String? = null, content: DocumentScope.() -> Unit): Document = texDocument(title = name.orEmpty(), header = {}, content = content)

fun texDocument(title: String?, autoMakeTitle: Boolean = true, header: TexHeaderScope.() -> Unit, content: DocumentScope.() -> Unit): Document {
	val headerBuilder = TexHeaderScope()
	headerBuilder.apply {
		title?.let { title(it) }
		header()
	}
	val contentBuilder = DocumentScope()
	contentBuilder.apply {
		title?.let { if (autoMakeTitle) makeTitle() }
		content()
	}
	return Document(
			name = title,
			header = headerBuilder.build(),
			content = contentBuilder.build()
	)
}

val StructureElement.ref: Reference get() = Reference(this)