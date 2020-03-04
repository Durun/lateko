package lateko.rendertest

import lateko.element.Document
import lateko.renderer.markdown.BasicMarkdownRenderer
import java.io.File
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager


private val scriptEngine: ScriptEngine = ScriptEngineManager().getEngineByExtension("kts")!!

internal fun generateDocument(script: File): Document {
	val reader = script.reader()
	println("Parsing: $script")
	val obj = runCatching { scriptEngine.eval(reader) }
			.getOrThrow()
	return runCatching { obj as Document }
			.getOrThrow()
}

internal fun generateMarkdown(document: Document): String {
	println("Generating Markdown...")
	return BasicMarkdownRenderer.render(document)
}

internal fun generateTex(document: Document): String {
	println("Generating TeX...")
	return document.accept(TODO())
}