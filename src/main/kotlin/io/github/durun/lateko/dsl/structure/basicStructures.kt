package io.github.durun.lateko.dsl.structure

import io.github.durun.lateko.dsl.chapterOf
import io.github.durun.lateko.dsl.inline.text
import io.github.durun.lateko.dsl.paragraphOf
import io.github.durun.lateko.dsl.sectionOf
import io.github.durun.lateko.extension.Paragraph
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.line.Line
import io.github.durun.lateko.model.line.Line.Companion.toLine
import io.github.durun.lateko.model.structure.Chapter
import io.github.durun.lateko.model.structure.Section


fun DocumentScope.chapter(
		title: InlineElement,
		label: String? = null,
		content: ChapterScope.() -> Unit
): Chapter = chapterOf(title, label = label, content = content).adding()

fun DocumentScope.chapter(
		title: String,
		label: String? = null,
		content: ChapterScope.() -> Unit
): Chapter = chapterOf(title, label = label, content = content).adding()


fun ChapterScope.section(
		name: InlineElement,
		label: String? = null,
		content: SectionScope.() -> Unit
): Section = sectionOf(name, label = label, content = content).adding()

fun ChapterScope.section(
		name: String,
		label: String? = null,
		content: SectionScope.() -> Unit
): Section = sectionOf(name, label = label, content = content).adding()


fun SectionScope.section(
		name: InlineElement,
		label: String? = null,
		content: SectionScope.() -> Unit
): Section = sectionOf(name, label = label, content = content).adding()

fun SectionScope.section(
		name: String,
		label: String? = null,
		content: SectionScope.() -> Unit
): Section = sectionOf(name, label = label, content = content).adding()


fun StructureScope.paragraph(content: ParagraphScope.() -> Unit): Paragraph = paragraphOf(content).adding()
fun StructureScope.p(content: ParagraphScope.() -> Unit): Paragraph = paragraph(content)

fun ParagraphScope.line(element: InlineElement): Line = element.toLine().adding()
fun ParagraphScope.line(element: String): Line = element.text.toLine().adding()
