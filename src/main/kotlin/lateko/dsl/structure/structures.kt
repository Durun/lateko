package lateko.dsl.structure

import lateko.dsl.inline.text
import lateko.dsl.structure.LineScope.Companion.build
import lateko.dsl.structure.StructureScope.Companion.build
import lateko.element.*
import lateko.element.Line.Companion.toLine

fun StructureScope.chapter(name: InlineElement, content: StructureScope.() -> Unit): Chapter = Chapter(name = name, content = content.build()).adding()
fun StructureScope.chapter(name: String, content: StructureScope.() -> Unit) = chapter(name.text, content)

fun StructureScope.section(name: InlineElement? = null, content: StructureScope.() -> Unit): Section = Section(name = name, content = content.build()).adding()
fun StructureScope.section(name: String, content: StructureScope.() -> Unit) = section(name.text, content)

fun StructureScope.paragraph(content: LineScope.() -> Unit): Paragraph = Paragraph(content.build()).adding()
fun StructureScope.p(content: LineScope.() -> Unit): Paragraph = paragraph(content)

fun LineScope.line(element: InlineElement): Line = element.toLine().adding()
fun LineScope.line(element: String): Line = element.text.toLine().adding()
