package lateko.command.tex

open class FontTexCommand(val style: String, val text: String) : TexCommand {
	override fun toString(): String {
		return "{\\$style $text}"
	}
}