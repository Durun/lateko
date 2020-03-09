package lateko.model.inline

interface InlineVisitor<R> {
	// Core
	fun visit(composition: InlineComposition): R
	//fun visit(lines: LineComposition): R
	//fun visit(line: Line): R

	// Basic
	fun visit(text: Text): R
	fun visit(code: EmbeddedCode): R

	// Ref
	fun visit(ref: Reference): R

	// URL
	fun visit(urlText: UrlText): R

	// StyledText
	fun visit(text: StyledText): R
}