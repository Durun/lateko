package lateko.command.tex

import lateko.command.Command

open class TexCommand(
		val command: String,
		val options: List<String> = emptyList(),
		val args: List<String> = emptyList()
) : Command {
	constructor(command: String, options: List<String> = emptyList(), arg: String) : this(command, options, args = listOf(arg))

	override fun toString(): String {
		val optionStr = options.takeIf { it.isNotEmpty() }
				?.let { "[${it.joinToString(",")}]" }
				.orEmpty()
		val argStr = args.joinToString("") { "{$it}" }
		return "\\$command$optionStr$argStr"
	}
}