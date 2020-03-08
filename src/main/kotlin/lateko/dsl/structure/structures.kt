package lateko.dsl.structure

import lateko.dsl.chapterOf
import lateko.dsl.inline.text
import lateko.dsl.paragraphOf
import lateko.dsl.sectionOf
import lateko.model.inline.InlineElement
import lateko.model.line.Line
import lateko.model.line.Line.Companion.toLine
import lateko.model.structure.Chapter
import lateko.model.structure.Paragraph
import lateko.model.structure.Section
import lateko.model.structure.StructureComposition
import lateko.model.structure.StructureComposition.Companion.toComposition

class DocumentScope : StructureScope()
class ChapterScope : StructureScope() {
	companion object {
		private fun StructureComposition.sortNest(): StructureComposition {
			return children.sortedBy { it is Section || it is Chapter }.toComposition()
		}

		fun (ChapterScope.() -> Unit).build(): StructureComposition {
			val builder = ChapterScope()
			builder.this()
			return builder.build().sortNest()
		}
	}
}

fun DocumentScope.chapter(title: InlineElement, content: ChapterScope.() -> Unit): Chapter = chapterOf(title, content).adding()
fun DocumentScope.chapter(title: String, content: ChapterScope.() -> Unit): Chapter = chapterOf(title, content).adding()

fun StructureScope.section(name: InlineElement? = null, content: StructureScope.() -> Unit): Section = sectionOf(name, content).adding()
fun StructureScope.section(name: String, content: StructureScope.() -> Unit): Section = sectionOf(name, content).adding()

fun StructureScope.paragraph(content: LineScope.() -> Unit): Paragraph = paragraphOf(content).adding()
fun StructureScope.p(content: LineScope.() -> Unit): Paragraph = paragraph(content)

fun LineScope.line(element: InlineElement): Line = element.toLine().adding()
fun LineScope.line(element: String): Line = element.text.toLine().adding()
