package lateko.dsl.inline

import lateko.model.EmbeddedCode


fun String.asMarkdown(): EmbeddedCode = this.embed(EmbeddedCode.Format.Markdown)
fun String.asTex(): EmbeddedCode = this.embed(EmbeddedCode.Format.Tex)
fun String.embed(format: EmbeddedCode.Format): EmbeddedCode {
	return EmbeddedCode(format = format, code = this)
}