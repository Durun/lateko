package io.github.durun.lateko.model.inline

interface InlineVisitor<R> {
	// Core
	fun visit(composition: InlineComposition): R

	// Basic
	fun visit(code: EmbeddedCode): R

	// Extension
	fun visit(element: InlineExtension): R

	// Ref
	fun visit(ref: Reference): R
}