package io.github.durun.lateko.rendertest

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

class TexRenderTests : FunSpec() {
	private val testDocDir: Path

	init {
		val dirName = "testDocuments"
		val resourceURL = this.javaClass.classLoader.getResource(dirName)
				?: throw NoSuchFileException(Paths.get("resourceURL").toFile())
		testDocDir = Paths.get(resourceURL.toURI())
	}

	private fun testScript(script: File) {
		val filePair = script.let { file ->
			val tex = Paths.get(file.absolutePath + ".tex").toFile().takeIf { it.exists() }
			Pair(file, tex)
		}

		filePair.let { (script, texFile) ->
			val document = generateDocument(script)
			val tex = generateTex(document)
			tex shouldBe texFile?.readText()
		}
	}

	init {
		val dir = testDocDir.toFile()
		dir.walk().toSet()
				.filter { it.extension == "kts" }
				.forEach { file ->
					var enabled: Boolean = true
					runCatching { generateTex(generateDocument(file)) }
							.onFailure { enabled = it !is NotImplementedError }
					if (!Paths.get(file.path + ".tex").toFile().exists()) enabled = false
					test(file.name).config(enabled = enabled) { testScript(file) }    // Submit test here
				}
	}
}