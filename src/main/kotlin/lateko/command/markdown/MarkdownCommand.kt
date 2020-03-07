package lateko.command.markdown

import lateko.command.Command

interface MarkdownCommand : Command {
	override fun toString(): String
}