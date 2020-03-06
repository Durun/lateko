package lateko.dsl.structure

import lateko.command.TexCommand
import lateko.dsl.inline.text
import lateko.dsl.structure.StructureScope.Companion.build
import lateko.element.Chapter
import lateko.element.InlineElement
import lateko.element.Section
import lateko.element.Structure


fun StructureScope.makeTitle(): Structure = TexCommand("maketitle").toLine().adding()

fun StructureScope.chapter(name: InlineElement, content: StructureScope.() -> Unit): Chapter = Chapter(name = name, content = content.build()).adding()
fun StructureScope.chapter(name: String, content: StructureScope.() -> Unit) = chapter(name.text, content)

fun StructureScope.section(name: InlineElement? = null, content: StructureScope.() -> Unit): Section = Section(name = name, content = content.build()).adding()
fun StructureScope.section(name: String, content: StructureScope.() -> Unit) = section(name.text, content)
