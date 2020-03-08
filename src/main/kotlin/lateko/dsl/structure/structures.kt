package lateko.dsl.structure

import lateko.dsl.chapterOf
import lateko.dsl.inline.text
import lateko.dsl.sectionOf
import lateko.dsl.structure.LineScope.Companion.build
import lateko.model.inline.InlineElement
import lateko.model.line.Line
import lateko.model.line.Line.Companion.toLine
import lateko.model.structure.Chapter
import lateko.model.structure.Paragraph
import lateko.model.structure.Section

fun StructureScope.chapter(title: InlineElement, content: StructureScope.() -> Unit): Chapter = chapterOf(title, content).adding()
fun StructureScope.chapter(title: String, content: StructureScope.() -> Unit): Chapter = chapterOf(title, content).adding()

fun StructureScope.section(name: InlineElement? = null, content: StructureScope.() -> Unit): Section = sectionOf(name, content).adding()
fun StructureScope.section(name: String, content: StructureScope.() -> Unit): Section = sectionOf(name, content).adding()

fun StructureScope.paragraph(content: LineScope.() -> Unit): Paragraph = Paragraph(content.build()).adding()
fun StructureScope.p(content: LineScope.() -> Unit): Paragraph = paragraph(content)

fun LineScope.line(element: InlineElement): Line = element.toLine().adding()
fun LineScope.line(element: String): Line = element.text.toLine().adding()
