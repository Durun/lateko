package io.github.durun.lateko.model.inline

import io.github.durun.lateko.model.Labeled
import io.github.durun.lateko.renderer.markdown.anchor
import io.github.durun.lateko.target.tex.Begin
import io.github.durun.lateko.target.tex.End

class SourceCode(
		private val title: String,
		private val content: String,
		override val label: String = title
) : InlineExtension, Labeled {
	override fun renderPlane(): String = content
	override fun renderTex(): String {
		return "${Begin("lstlisting")}[caption=$title,label=$label]\n${content.prependIndent("\t")}\n${End("lstlisting")}"
	}

	override fun renderMarkdown(): String {
		return "$anchor\n\n```\n$content\n```"
	}
}