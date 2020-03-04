package lateko.renderer.tex

class DocumentClass(options: List<String>, name: String) : TexCommand("documentclass", options, arg = name)
class UsePackage(options: List<String>, name: String) : TexCommand("usepackage", options, arg = name)
class RequirePackage(name: String) : TexCommand("RequirePackage", arg = name)
class Title(name: String) : TexCommand("title", arg = name)
class Author(name: String) : TexCommand("author", arg = name)
class Date(date: String) : TexCommand("date", arg = date)
object MakeTitle : TexCommand("maketitle")


open class TexCommand(
		val command: String,
		val options: List<String> = emptyList(),
		val args: List<String> = emptyList()
) {
	constructor(command: String, options: List<String> = emptyList(), arg: String) : this(command, options, args = listOf(arg))

	override fun toString(): String {
		val optionStr = options.takeIf { it.isNotEmpty() }
				?.let { "[${it.joinToString(",")}]" }
				.orEmpty()
		val argStr = args.joinToString("") { "{$it}" }
		return "\\$command$optionStr$argStr"
	}
}