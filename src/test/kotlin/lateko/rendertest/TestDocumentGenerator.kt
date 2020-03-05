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
		val file = Paths.get(ktsFile.path + ".$extension").toFile()
		runCatching {
			if (file.exists()) throw FileAlreadyExistsException(file)
			val fileText = content.value
			file.createNewFile()
			fileText
		}.onFailure {
			System.err.println("""Skip saving $extension: $file
				|	${it::class.simpleName}: ${it.message}
				|	at ${it.stackTrace.first()}
			""".trimMargin())
		}.onSuccess {
			println("Saving $extension: $file")
			file.writeText(it)
		}
	}

	private fun saveDocuments(script: File) {
		val document = lazy { generateDocument(script) }
		val markdown = lazy { generateMarkdown(document.value) }
		val tex = lazy { generateTex(document.value) }
		saveDocumentIfNotExists(ktsFile = script, extension = "md", content = markdown)
		saveDocumentIfNotExists(ktsFile = script, extension = "tex", content = tex)
		if (document.isInitialized()) println(document.value)
	}
}
