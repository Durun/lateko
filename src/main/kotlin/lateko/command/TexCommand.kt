package lateko.command

class DocumentClass(options: List<String>, name: String) : TexCommand("documentclass", options, arg = name)
class UsePackage(options: List<String>, name: String) : TexCommand("usepackage", options, arg = name) {
	constructor(name: String) : this(options = emptyList(), name = name)
	constructor(name: String, option: String) : this(options = listOf(option), name = name)
}

class RequirePackage(name: String) : TexCommand("RequirePackage", arg = name)
class Title(name: String) : TexCommand("title", arg = name)
class Author(name: String) : TexCommand("author", arg = name)
class Date(date: String) : TexCommand("date", arg = date)
object MakeTitle : TexCommand("maketitle")
class Begin(name: String) : TexCommand("begin", arg = name)
class End(name: String) : TexCommand("end", arg = name)
class Chapter(name: String) : TexCommand("chapter", arg = name)
class Section(name: String) : TexCommand("section", arg = name)
class SubSection(name: String) : TexCommand("subsection", arg = name)
class SubSubSection(name: String) : TexCommand("subsubsection", arg = name)

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