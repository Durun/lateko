package io.github.durun.lateko.model.structure

import io.github.durun.lateko.model.Document

interface StructureVisitor<R> {
	fun visit(structure: Structure): R
	fun visit(composition: StructureComposition): R
	fun visit(section: Section): R
	fun visit(chapter: Chapter): R
	fun visit(document: Document): R

	// Extensions
	fun visit(structure: StructureExtension): R
}