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
import io.github.durun.lateko.model.inline.StructureReference
import io.github.durun.lateko.model.structure.Chapter
import io.github.durun.lateko.model.structure.Section
import io.github.durun.lateko.model.structure.StructureContext
import io.github.durun.lateko.model.structure.StructureElement

fun paragraphOf(content: ParagraphScope.() -> Unit): Paragraph = Paragraph(content.build())

fun sectionOf(title: String, label: String? = null, chapterName: String? = null, content: SectionScope.() -> Unit): Section {
	return sectionOf(title.text, label, chapterName, content)
}

fun sectionOf(
		title: InlineElement? = null,
		label: String? = null,
		chapterName: String? = null,
		content: SectionScope.() -> Unit
): Section {
	val context = StructureContext(sectionIdPath = listOfNotNull(chapterName))
	return Section(name = title, idName = label, content = content.build(), context = context)
}


fun chapterOf(title: InlineElement, label: String? = null, content: ChapterScope.() -> Unit): Chapter = Chapter(name = title, idName = label, content = content.build())
fun chapterOf(title: String, label: String? = null, content: ChapterScope.() -> Unit): Chapter = chapterOf(title = title.text, label = label, content = content)


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

val StructureElement.ref: StructureReference get() = StructureReference(this)