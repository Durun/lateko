package lateko.rendertest

import lateko.element.Document
import lateko.renderer.MarkdownRenderer
import lateko.renderer.TexRenderer
import lateko.renderer.markdown.BasicMarkdownRenderer
import lateko.renderer.tex.BasicTexRenderer
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
	val renderer: MarkdownRenderer = BasicMarkdownRenderer
	println("Generating Markdown...")
	return renderer.render(document)
}

internal fun generateTex(document: Document): String {
	val renderer: TexRenderer = BasicTexRenderer
	println("Generating TeX...")
	return renderer.render(document)
}