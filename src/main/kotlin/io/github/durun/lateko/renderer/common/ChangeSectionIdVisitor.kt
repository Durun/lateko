package io.github.durun.lateko.renderer.common

import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.inline.EmbeddedCode
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.inline.Reference
import io.github.durun.lateko.model.structure.*
import io.github.durun.lateko.model.structure.StructureComposition.Companion.toComposition

class ChangeSectionIdVisitor : StructureVisitor<StructureElement> {
	private var sectionNestLevel = -1
	private val nameStack: MutableList<String> = mutableListOf()
	private fun MutableList<String>.push(name: String) {
		this.add(name)
	}

	private fun MutableList<String>.pop(): String {
		val last = this.last()
		this.removeAt(lastIndex)
		return last
	}

	private fun MutableList<String>.toId() = this.joinToString("/") { it.escape() }

	private fun InlineElement.getText(): String = this.accept(GetTextVisitor)

	override fun visit(structure: Structure): StructureElement = structure

	override fun visit(composition: StructureComposition): StructureElement {
		return composition.children.map {
			it.accept(this)
		}.toComposition()
	}

	override fun visit(paragraph: Paragraph): StructureElement = paragraph

	override fun visit(section: Section): StructureElement {
		sectionNestLevel++
		nameStack.push(section.name?.getText() ?: section.id)
		section.content.accept(this)
		section.changeId("${"sub".repeat(sectionNestLevel)}sec:${nameStack.toId()}")
		nameStack.pop()
		sectionNestLevel--
		return section
	}

	override fun visit(chapter: Chapter): StructureElement {
		if (chapter.id.startsWith("ch:")) return chapter // skip

		nameStack.push(chapter.name.getText())
		chapter.content.accept(this)
		chapter.changeId("ch:${nameStack.toId()}")
		nameStack.pop()
		return chapter
	}

	override fun visit(document: Document): StructureElement {
		document.content.accept(this)
		return document
	}

	private object GetTextVisitor : InlineRenderVisitor {
		override fun outputFormat(): EmbeddedCode.Format = EmbeddedCode.Format.None
		override fun visit(ref: Reference): String = throw IllegalStateException()
	}

	private fun String.escape(): String {
		return this.replace('#', '＃')
				.replace('&', '＆')
				.replace('%', '％')
				.replace('{', '｛')
				.replace('}', '｝')
				.replace(" ", "")
		// TODO
	}
}