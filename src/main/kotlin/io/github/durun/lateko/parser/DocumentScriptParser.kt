package io.github.durun.lateko.parser

import io.github.durun.lateko.model.structure.StructureElement
import java.io.File
import javax.script.ScriptEngineManager


class DocumentScriptParser {
	companion object {
		private val scriptEngine = ScriptEngineManager().getEngineByExtension("kts")!!
	}

	fun parse(file: File): StructureElement {
		val reader = file.reader()
		val obj = runCatching { scriptEngine.eval(reader) }.getOrThrow()
		return runCatching { obj as StructureElement }.getOrThrow()
	}
}