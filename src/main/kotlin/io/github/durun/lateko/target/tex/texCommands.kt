package io.github.durun.lateko.target.tex

class Chapter(name: String) : SimpleTexCommand("chapter", arg = name)
class Section(name: String) : SimpleTexCommand("section", arg = name)
class SubSection(name: String) : SimpleTexCommand("subsection", arg = name)
class SubSubSection(name: String) : SimpleTexCommand("subsubsection", arg = name)

class Begin(name: String, vararg options: String) : SimpleTexCommand("begin", arg = name, options = options.asList())
class End(name: String) : SimpleTexCommand("end", arg = name)


class Title(name: String) : SimpleTexCommand("title", arg = name)
class Author(name: String) : SimpleTexCommand("author", arg = name)
class Date(date: String) : SimpleTexCommand("date", arg = date)

object MakeTitle : SimpleTexCommand("maketitle")
object TableOfContents : SimpleTexCommand("tableofcontents")
object ListOfFigures : SimpleTexCommand("listoffigures")
object ListOfTables : SimpleTexCommand("listoftables")

class DocumentClass(options: List<String>, name: String) : SimpleTexCommand("documentclass", options, arg = name)
class RequirePackage(name: String) : SimpleTexCommand("RequirePackage", arg = name) {
	val packageName: String get() = arg ?: throw IllegalStateException()
}

class UsePackage(options: List<String>, name: String) : SimpleTexCommand("usepackage", options, arg = name) {
	constructor(name: String) : this(options = emptyList(), name = name)
	constructor(name: String, option: String) : this(options = listOf(option), name = name)

	val packageName: String get() = arg ?: throw IllegalStateException()
}

class Label(id: String) : SimpleTexCommand("label", arg = id)
class Ref(label: String) : SimpleTexCommand("ref", arg = label)
//TODO class Cite

class Caption(title: String) : SimpleTexCommand("caption", arg = title)

object Centering : SimpleTexCommand("centering")

class BoldText(text: String) : SimpleTexCommand("textbf", arg = text)
class ItalicText(text: String) : SimpleTexCommand("textsl", arg = text)
class StrongItalicText(text: String) : SimpleTexCommand("textit", arg = text)
class TypewriterText(text: String) : SimpleTexCommand("texttt", arg = text)
class SmallCapsText(text: String) : SimpleTexCommand("textsc", arg = text)