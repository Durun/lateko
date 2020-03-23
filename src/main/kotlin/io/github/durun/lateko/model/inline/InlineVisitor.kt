package io.github.durun.lateko.model.inline

interface InlineVisitor<R> {
	// Core
	fun visit(composition: InlineComposition): R
	//fun visit(lines: LineComposition): R
	//fun visit(line: Line): R

	// Basic
	fun visit(text: Text): R
	fun visit(code: EmbeddedCode): R

	// Extension
	fun visit(element: InlineExtension): R

	// Ref
	fun visit(ref: Reference): R

	// StyledText
	fun visit(text: StyledText): R
}