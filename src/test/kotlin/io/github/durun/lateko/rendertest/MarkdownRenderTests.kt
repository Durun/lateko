package io.github.durun.lateko.rendertest

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

class MarkdownRenderTests : FunSpec() {
	private val testDocDir: Path

	init {
		val dirName = "testDocuments"
		val resourceURL = this.javaClass.classLoader.getResource(dirName)
				?: throw NoSuchFileException(Paths.get("resourceURL").toFile())
		testDocDir = Paths.get(resourceURL.toURI())
	}

	private fun testScript(script: File) {
		val filePair = script.let { file ->
			val markdown = Paths.get(file.absolutePath + ".md").toFile().takeIf { it.exists() }
			Pair(file, markdown)
		}

		filePair.let { (script, mdFile) ->
			val document = generateDocument(script)
			val markdown = generateMarkdown(document)
			markdown shouldBe mdFile?.readText()
		}
	}

	init {
		val dir = testDocDir.toFile()
		dir.walk().toSet()
				.filter { it.extension == "kts" }
				.forEach { file ->
					var enabled = true
					runCatching { generateMarkdown(generateDocument(file)) }
							.onFailure { enabled = it !is NotImplementedError }
					if (!Paths.get(file.path + ".md").toFile().exists()) enabled = false
					test(file.name).config(enabled = enabled) { testScript(file) }    // Submit test here
				}
	}
}