package lateko.dsl.header

import lateko.command.Command
import lateko.command.tex.*
import lateko.dsl.Builder
import lateko.model.inline.EmbeddedCode
import lateko.model.line.Line
import lateko.model.line.Line.Companion.toLine
import lateko.model.line.LineComposition
import lateko.model.line.LineComposition.Companion.toComposition
import lateko.model.line.LineElement
import lateko.renderer.tex.TexEscaper

open class TexHeaderScope : Builder<LineElement>() {
	companion object {
		private val defaultDocumentClass = DocumentClass(name = "jsbook", options = listOf("a4paper", "11pt", "oneside", "openany", "report"))
		private val defaultUsePackages = listOf(
				UsePackage(name = "hyperref", option = "dvipdfmx"),
				UsePackage(name = "pxjahyper")
		)
	}

	private val format = EmbeddedCode.Format.Tex
	private fun Command.toLine(): Line = EmbeddedCode(this.toString(), format = format).toLine()
	private fun String.escape() = TexEscaper.escape(this)

	private var documentClass = defaultDocumentClass
	private var usePackages = defaultUsePackages.toMutableList()

	fun documentClass(className: String, vararg options: String) {
		documentClass = DocumentClass(name = className, options = options.asList())
	}

	fun usePackage(packageName: String, vararg options: String) {
		usePackages.add(UsePackage(name = packageName, options = options.asList()))
	}

	internal fun title(title: String): Line {
		return Title(title.escape()).toLine().adding()
	}

	fun author(author: String): Line = Author(author).toLine().adding()

	fun date(date: String): Line = Date(date).toLine().adding()

	override fun build(): LineComposition {
		val header =
				listOf(documentClass) +
						usePackages
		header.asReversed().forEach {
			it.toLine().addingFirst()
		}
		return elements.toComposition()
	}
}