package lateko.rendertest

import io.kotest.assertions.forEachAsClue
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import lateko.element.Document
import lateko.renderer.MarkdownRenderVisitor
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class RenderTests : StringSpec() {
	private val scriptEngine: ScriptEngine = ScriptEngineManager().getEngineByExtension("kts")!!
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
			val tex = Paths.get(file.absolutePath + ".tex").toFile().takeIf { it.exists() }
			Triple(file, markdown, tex)
		}

		filePair.let { (script, mdFile, texFile) ->
			val document = generateDocument(script)
			val markdown = generateMarkdown(document)
			// TODO: val tex = generateTex(document)
			markdown shouldBe mdFile?.readText()
			// TODO: tex shouldBe texFile?.readText()
		}
	}

	init {
		val dir = testDocDir.toFile()
		dir.walk().toSet()
				.filter { it.extension == "kts" }
				.forEach { file ->
					file.name { testScript(file) }    // Submit test here
				}
	}
}