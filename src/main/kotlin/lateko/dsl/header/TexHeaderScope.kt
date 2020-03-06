package lateko.dsl.header

import lateko.command.*
import lateko.dsl.Builder
import lateko.model.EmbeddedCode
import lateko.model.Line
import lateko.model.Line.Companion.toLine
import lateko.model.LineComposition
import lateko.model.LineComposition.Companion.toComposition
import lateko.model.LineElement

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

	private var documentClass = defaultDocumentClass
	private var usePackages = defaultUsePackages.toMutableList()

	fun documentClass(className: String, vararg options: String) {
		documentClass = DocumentClass(name = className, options = options.asList())
	}

	fun usePackage(packageName: String, vararg options: String) {
		usePackages.add(UsePackage(name = packageName, options = options.asList()))
	}

	internal fun title(title: String): Line {
		return Title(title).toLine().adding()
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