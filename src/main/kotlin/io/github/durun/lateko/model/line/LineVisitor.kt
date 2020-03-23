package io.github.durun.lateko.model.line

interface LineVisitor<R> {
	// Core
	fun visit(lines: LineComposition): R
	fun visit(line: Line): R

	// Extensions
	fun visit(lines: LineExtension): R
}