package io.github.durun.lateko.dsl.header

import io.github.durun.lateko.target.Command
import io.github.durun.lateko.target.tex.*
import io.github.durun.lateko.dsl.Builder
import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.line.Line
import io.github.durun.lateko.model.line.Line.Companion.toLine
import io.github.durun.lateko.model.line.LineComposition
import io.github.durun.lateko.model.line.LineComposition.Companion.toComposition
import io.github.durun.lateko.model.line.LineElement
import io.github.durun.lateko.renderer.tex.TexEscaper

open class TexHeaderScope : Builder<LineElement>() {
	companion object {
		private val defaultDocumentClass = DocumentClass(name = "jsbook", options = listOf("a4paper", "11pt", "oneside", "openany", "report"))
		private val defaultUsePackages: List<UsePackage> = listOf(
				UsePackage(name = "hyperref", option = "dvipdfmx"),
				UsePackage(name = "pxjahyper")
		)
		private val defaultRequirePackages: List<RequirePackage> = listOf()

		private fun MutableList<UsePackage>.addOrUpdate(usePackage: UsePackage) {
			this.removeIf { it.packageName == usePackage.packageName }
			this.add(usePackage)
		}

		private fun MutableList<RequirePackage>.addOrUpdate(requirePackage: RequirePackage) {
			this.removeIf { it.packageName == requirePackage.packageName }
			this.add(requirePackage)
		}
	}

	private val format = Format.Tex
	private fun Command.toLine(): Line = EmbeddedCode(this.toString(), format = format).toLine()
	private fun String.escape() = TexEscaper.escape(this)

	private var documentClass = defaultDocumentClass
	private val usePackages = defaultUsePackages.toMutableList()
	private val requirePackages = defaultRequirePackages.toMutableList()

	fun documentClass(className: String, vararg options: String) {
		documentClass = DocumentClass(name = className, options = options.asList())
	}

	fun usePackage(packageName: String, vararg options: String) {
		usePackages.addOrUpdate(UsePackage(name = packageName, options = options.asList()))
	}

	fun requirePackage(packageName: String) {
		requirePackages.addOrUpdate(RequirePackage(packageName))
	}

	internal fun title(title: String): Line {
		return Title(title.escape()).toLine().adding()
	}

	fun author(author: String): Line = Author(author).toLine().adding()

	fun date(date: String): Line = Date(date).toLine().adding()

	override fun build(): LineComposition {
		val header =
				listOf(documentClass) +
						usePackages +
						requirePackages
		header.asReversed().forEach {
			it.toLine().addingFirst()
		}
		return elements.toComposition()
	}
}