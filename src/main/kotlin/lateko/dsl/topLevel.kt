package lateko.dsl

import lateko.dsl.command.makeTitle
import lateko.dsl.header.TexHeaderScope
import lateko.dsl.inline.text
import lateko.dsl.structure.LineScope
import lateko.dsl.structure.LineScope.Companion.build
import lateko.dsl.structure.StructureScope
import lateko.dsl.structure.StructureScope.Companion.build
import lateko.model.Document
import lateko.model.inline.InlineElement
import lateko.model.inline.Reference
import lateko.model.structure.Chapter
import lateko.model.structure.Paragraph
import lateko.model.structure.Section
import lateko.model.structure.StructureElement

fun paragraphOf(content: LineScope.() -> Unit): Paragraph = Paragraph(content.build())

fun sectionOf(title: InlineElement? = null, content: StructureScope.() -> Unit) = Section(name = title, content = content.build())
fun sectionOf(title: String, content: StructureScope.() -> Unit) = sectionOf(title = title.text, content = content)

fun chapterOf(title: InlineElement, content: StructureScope.() -> Unit): Chapter = Chapter(name = title, content = content.build())
fun chapterOf(title: String, content: StructureScope.() -> Unit): Chapter = chapterOf(title = title.text, content = content)

fun document(name: String? = null, content: StructureScope.() -> Unit): Document = texDocument(title = name.orEmpty(), header = {}, content = content)

fun texDocument(title: String?, header: TexHeaderScope.() -> Unit, content: StructureScope.() -> Unit): Document {
	val headerBuilder = TexHeaderScope()
	headerBuilder.apply {
		title?.let { title(it) }
		header()
	}
	val contentBuilder = StructureScope()
	contentBuilder.apply {
		title?.let { makeTitle() }
		content()
	}
	return Document(
			name = title,
			header = headerBuilder.build(),
			content = contentBuilder.build()
	)
}

val StructureElement.ref: Reference get() = Reference(this)