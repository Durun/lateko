package io.github.durun.lateko.rendertest

import java.io.File
import java.nio.file.Paths
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

fun main() = TestDocumentGenerator.main()

object TestDocumentGenerator {
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
			val out = if (it is FileAlreadyExistsException) System.out else System.err
			out.println("""Skip saving $extension: $file
				|	${it::class.simpleName}: ${it.message}
				|	at ${it.stackTrace.first()}
			""".trimMargin())
		}.onSuccess {
			println("Saving $extension: $file")
			file.writeText(it)
		}
	}

	private fun saveDocuments(script: File) {
		val markdown = lazy { generateMarkdown(generateDocument(script)) }
		val tex = lazy { generateTex(generateDocument(script)) }
		saveDocumentIfNotExists(ktsFile = script, extension = "md", content = markdown)
		saveDocumentIfNotExists(ktsFile = script, extension = "tex", content = tex)
	}
}
