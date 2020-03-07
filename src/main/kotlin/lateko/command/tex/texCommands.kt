package lateko.command.tex

class Chapter(name: String) : TexCommand("chapter", arg = name)
class Section(name: String) : TexCommand("section", arg = name)
class SubSection(name: String) : TexCommand("subsection", arg = name)
class SubSubSection(name: String) : TexCommand("subsubsection", arg = name)

class Begin(name: String) : TexCommand("begin", arg = name)
class End(name: String) : TexCommand("end", arg = name)

class Title(name: String) : TexCommand("title", arg = name)
class Author(name: String) : TexCommand("author", arg = name)
class Date(date: String) : TexCommand("date", arg = date)
object MakeTitle : TexCommand("maketitle")

class DocumentClass(options: List<String>, name: String) : TexCommand("documentclass", options, arg = name)
class RequirePackage(name: String) : TexCommand("RequirePackage", arg = name)
class UsePackage(options: List<String>, name: String) : TexCommand("usepackage", options, arg = name) {
	constructor(name: String) : this(options = emptyList(), name = name)
	constructor(name: String, option: String) : this(options = listOf(option), name = name)
}
