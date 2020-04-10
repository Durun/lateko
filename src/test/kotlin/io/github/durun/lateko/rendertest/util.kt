package io.github.durun.lateko.rendertest

import io.github.durun.lateko.model.structure.StructureElement
import io.github.durun.lateko.parser.DocumentScriptParser
import io.github.durun.lateko.renderer.markdown.BasicMarkdownRenderer
import io.github.durun.lateko.renderer.markdown.MarkdownRenderer
import io.github.durun.lateko.renderer.tex.BasicTexRenderer
import io.github.durun.lateko.renderer.tex.TexRenderer
import java.io.File


internal fun generateDocument(script: File): StructureElement {
	val reader = script.reader()
	println("Parsing: $script")
	return DocumentScriptParser().parse(script)
}

internal fun generateMarkdown(document: StructureElement): String {
	val renderer: MarkdownRenderer = BasicMarkdownRenderer
	println("Generating Markdown...")
	return renderer.render(document)
}

internal fun generateTex(document: StructureElement): String {
	val renderer: TexRenderer = BasicTexRenderer
	println("Generating TeX...")
	return renderer.render(document)
}