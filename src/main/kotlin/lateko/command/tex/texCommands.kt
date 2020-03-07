package lateko.command.tex

class Chapter(name: String) : SimpleTexCommand("chapter", arg = name)
class Section(name: String) : SimpleTexCommand("section", arg = name)
class SubSection(name: String) : SimpleTexCommand("subsection", arg = name)
class SubSubSection(name: String) : SimpleTexCommand("subsubsection", arg = name)

class Begin(name: String) : SimpleTexCommand("begin", arg = name)
class End(name: String) : SimpleTexCommand("end", arg = name)

class Title(name: String) : SimpleTexCommand("title", arg = name)
class Author(name: String) : SimpleTexCommand("author", arg = name)
class Date(date: String) : SimpleTexCommand("date", arg = date)
object MakeTitle : SimpleTexCommand("maketitle")

class DocumentClass(options: List<String>, name: String) : SimpleTexCommand("documentclass", options, arg = name)
class RequirePackage(name: String) : SimpleTexCommand("RequirePackage", arg = name)
class UsePackage(options: List<String>, name: String) : SimpleTexCommand("usepackage", options, arg = name) {
	constructor(name: String) : this(options = emptyList(), name = name)
	constructor(name: String, option: String) : this(options = listOf(option), name = name)
}

class BoldText(text: String) : FontTexCommand(style = "bf", text = text)
class ItalicText(text: String) : FontTexCommand(style = "sl", text = text)
class StrongItalicText(text: String) : FontTexCommand(style = "it", text = text)
class TypewriterText(text: String) : FontTexCommand(style = "tt", text = text)
