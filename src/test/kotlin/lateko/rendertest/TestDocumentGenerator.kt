package lateko.rendertest

import java.io.File
import java.nio.file.Paths
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

fun main() = TestDocumentGenerator.main()

object TestDocumentGenerator {
	private val scriptEngine: ScriptEngine = ScriptEngineManager().getEngineByExtension("kts")!!
	fun main() {
		val dirName = "testDocuments"
		val dir = Paths.get("src/test/resources/$dirName").toFile()

		dir.walk().toSet()
				.filter { it.extension == "kts" }
				.forEach { file ->
					saveDocuments(file)
				}
	}

	private fun saveDocumentIfNotExists(ktsFile: File, extension: String, content: Lazy<String>) {
		val newFile = Paths.get(ktsFile.path + ".$extension").toFile()
		newFile.takeIf { !it.exists() }?.let { file ->
			println("Saving as $extension: $file")
			file.createNewFile()
			file.writeText(content.value)
		} ?: println("Already exists: $newFile")
	}

	private fun saveDocuments(script: File) {
		val document = lazy { generateDocument(script) }
		val markdown = lazy { generateMarkdown(document.value) }
		val tex = lazy { generateTex(document.value) }

		saveDocumentIfNotExists(ktsFile = script, extension = "md", content = markdown)
		// TODO: saveDocumentIfNotExists(ktsFile = script, extension = "tex", content = tex)
	}
}
