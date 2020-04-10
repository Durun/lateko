package io.github.durun.lateko.app

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.types.path
import io.github.durun.lateko.model.structure.StructureElement
import io.github.durun.lateko.parser.DocumentScriptParser
import io.github.durun.lateko.renderer.common.Renderer
import io.github.durun.lateko.renderer.markdown.BasicMarkdownRenderer
import io.github.durun.lateko.renderer.tex.BasicTexRenderer
import io.github.durun.lib.path.mapName
import java.nio.file.Path

class CompileApp : CliktCommand(name = "compile") {
	private val inputPaths by argument("input")
			.path(
					mustBeReadable = true,
					canBeDir = false
			).multiple()

	override fun run() {
		inputPaths.forEach { inputPath ->
			processFile(inputPath)
		}
	}

	private fun processFile(input: Path) {
		val document = DocumentScriptParser().parse(input.toFile())
		val output = input.mapName { it.nameWithoutExtension }
		val texOutPath = output.mapName { it.name + ".tex" }
		val mdOutPath = output.mapName { it.name + ".md" }
		document.write(texOutPath, BasicTexRenderer)
		document.write(mdOutPath, BasicMarkdownRenderer)
	}

	private fun StructureElement.write(path: Path, renderer: Renderer) {
		renderer.render(this).let { content ->
			val newFile = path.toFile()
			newFile.createNewFile()
			newFile.writeText(content)
		}
	}
}