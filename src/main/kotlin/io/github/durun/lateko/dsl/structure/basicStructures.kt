package io.github.durun.lateko.dsl.structure

import io.github.durun.lateko.dsl.chapterOf
import io.github.durun.lateko.dsl.inline.text
import io.github.durun.lateko.dsl.paragraphOf
import io.github.durun.lateko.dsl.sectionOf
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.line.Line
import io.github.durun.lateko.model.line.Line.Companion.toLine
import io.github.durun.lateko.model.structure.Chapter
import io.github.durun.lateko.model.structure.Paragraph
import io.github.durun.lateko.model.structure.Section

fun DocumentScope.chapter(title: InlineElement, content: ChapterScope.() -> Unit): Chapter = chapterOf(title, content).adding()
fun DocumentScope.chapter(title: String, content: ChapterScope.() -> Unit): Chapter = chapterOf(title, content).adding()

fun ChapterScope.section(name: InlineElement? = null, content: SectionScope.() -> Unit): Section = sectionOf(name, content).adding()
fun ChapterScope.section(name: String, content: SectionScope.() -> Unit): Section = sectionOf(name, content).adding()

fun SectionScope.section(name: InlineElement? = null, content: SectionScope.() -> Unit): Section = sectionOf(name, content).adding()
fun SectionScope.section(name: String, content: SectionScope.() -> Unit): Section = sectionOf(name, content).adding()

fun StructureScope.paragraph(content: ParagraphScope.() -> Unit): Paragraph = paragraphOf(content).adding()
fun StructureScope.p(content: ParagraphScope.() -> Unit): Paragraph = paragraph(content)

fun ParagraphScope.line(element: InlineElement): Line = element.toLine().adding()
fun ParagraphScope.line(element: String): Line = element.text.toLine().adding()
