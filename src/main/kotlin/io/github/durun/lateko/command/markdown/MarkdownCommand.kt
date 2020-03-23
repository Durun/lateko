package io.github.durun.lateko.command.markdown

import io.github.durun.lateko.command.Command

interface MarkdownCommand : Command {
	override fun toString(): String
}