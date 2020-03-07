package lateko.model.inline

import lateko.model.line.Line
import lateko.model.line.LineComposition

interface InlineVisitor<R> {
	// Core
	fun visit(composition: InlineComposition): R
	fun visit(lines: LineComposition): R
	fun visit(line: Line): R

	// Basic
	fun visit(text: Text): R
	fun visit(code: EmbeddedCode): R

	// URL
	fun visit(urlText: UrlText): R
}