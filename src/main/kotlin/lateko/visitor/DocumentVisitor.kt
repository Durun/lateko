package lateko.visitor

import lateko.model.*
import lateko.model.inline.EmbeddedCode
import lateko.model.inline.Text
import lateko.model.inline.UrlText

interface DocumentVisitor<R> : StructureVisitor<R>, InlineVisitor<R>

interface StructureVisitor<R> {
	fun visit(structure: Structure): R
	fun visit(composition: StructureComposition): R
	fun visit(paragraph: Paragraph): R
	fun visit(section: Section): R
	fun visit(chapter: Chapter): R
	fun visit(document: Document): R
}

interface InlineVisitor<R> {
	fun visit(text: Text): R
	fun visit(urlText: UrlText): R
	fun visit(composition: InlineComposition): R
	fun visit(code: EmbeddedCode): R
	fun visit(line: Line): R
	fun visit(lines: LineComposition): R
}