package lateko.model.structure

import lateko.model.Document

interface StructureVisitor<R> {
	fun visit(structure: Structure): R
	fun visit(composition: StructureComposition): R
	fun visit(paragraph: Paragraph): R
	fun visit(section: Section): R
	fun visit(chapter: Chapter): R
	fun visit(document: Document): R
}