package lateko.model.inline

import lateko.model.line.Line
import lateko.model.line.LineComposition

interface InlineVisitor<R> {
	fun visit(text: Text): R
	fun visit(urlText: UrlText): R
	fun visit(composition: InlineComposition): R
	fun visit(code: EmbeddedCode): R
	fun visit(line: Line): R
	fun visit(lines: LineComposition): R
}