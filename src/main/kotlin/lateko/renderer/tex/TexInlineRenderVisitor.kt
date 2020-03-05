package lateko.renderer.tex

import lateko.element.*
import lateko.visitor.InlineRenderVisitor


internal object TexInlineRenderVisitor : InlineRenderVisitor {
	override fun EmbeddedCode.isEnabled(): Boolean = this.format == EmbeddedCode.Format.Tex

	override fun visit(text: Text): String = TexEscaper.escape(text.text)

	override fun visit(urlText: UrlText): String {
		TODO("Not yet implemented")
	}

	override fun visit(composition: InlineComposition): String {
		return composition.children.joinToString("") { it.rendered }
	}

	override fun visit(code: EmbeddedCode): String {
		val str = code.takeIf { it.isEnabled() }?.code
		return str.orEmpty()
	}

	override fun visit(line: Line): String {
		val lineStr = line.element.accept(this) + "\n"
		return lineStr.takeUnless {
			line.element is EmbeddedCode && !line.element.isEnabled()
		}.orEmpty()
	}

	override fun visit(lines: LineComposition): String {
		return lines.children.joinToString("") { it.rendered }
	}
}


