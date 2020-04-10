package io.github.durun.lateko.model.inline

import io.github.durun.lateko.model.Format

data class EmbeddedCode(val code: String, val format: Format) : InlineExtension {
	override fun renderTex(): String {
		return code.takeIf { format == Format.Tex }.orEmpty()
	}

	override fun renderMarkdown(): String {
		return code.takeIf { format == Format.Markdown }.orEmpty()
	}
}