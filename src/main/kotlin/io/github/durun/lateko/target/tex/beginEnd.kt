package io.github.durun.lateko.target.tex

class BeginEnd(name: String, vararg options: String = emptyArray(), content: () -> String) : TexCommand {
	val begin = Begin(name, *options)
	val content = content()
	val end = End(name)
	override fun toString(): String {
		return """$begin
			|${content.prependIndent("\t")}
		|$end""".trimMargin()
	}
}