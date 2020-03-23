package io.github.durun.lateko.dsl.inline

import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.inline.EmbeddedCode


fun String.asMarkdown(): EmbeddedCode = this.embed(Format.Markdown)
fun String.asTex(): EmbeddedCode = this.embed(Format.Tex)
fun String.embed(format: Format): EmbeddedCode {
	return EmbeddedCode(format = format, code = this)
}