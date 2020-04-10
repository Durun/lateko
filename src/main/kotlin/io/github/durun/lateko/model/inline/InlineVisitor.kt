package io.github.durun.lateko.model.inline

interface InlineVisitor<R> {
	// Core
	fun visit(composition: InlineComposition): R

	// Extension
	fun visit(element: InlineExtension): R

	// Ref
	fun visit(ref: Reference): R
}