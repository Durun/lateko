package io.github.durun.lateko.target.markdown

import io.github.durun.lateko.target.Command

interface MarkdownCommand : Command {
	override fun toString(): String
}