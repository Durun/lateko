package io.github.durun.lateko.renderer.common

import io.github.durun.lateko.model.Document
import io.github.durun.lateko.model.Format
import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.inline.Reference
import io.github.durun.lateko.model.structure.*
import io.github.durun.lateko.model.structure.StructureComposition.Companion.toComposition

class ChangeSectionIdVisitor(context: StructureContext? = null) : StructureVisitor<StructureElement> {
	private val nameStack: MutableList<String> = (context?.sectionIdPath ?: emptyList()).toMutableList()
	private var sectionNestLevel = -1
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

	override fun visit(section: Section): StructureElement {
		sectionNestLevel++
		nameStack.push(section.name?.getText() ?: section.id)
		section.content.accept(this)
		if (section.isIdDefault()) section.changeId("${"sub".repeat(sectionNestLevel)}sec:${nameStack.toId()}")
		nameStack.pop()
		sectionNestLevel--
		return section
	}

	override fun visit(chapter: Chapter): StructureElement {
		if (chapter.id.startsWith("ch:")) return chapter // skip

		nameStack.push(chapter.name.getText())
		chapter.content.accept(this)
		if (chapter.isIdDefault()) chapter.changeId("ch:${nameStack.toId()}")
		nameStack.pop()
		return chapter
	}

	override fun visit(document: Document): StructureElement {
		document.content.accept(this)
		return document
	}

	override fun visit(structure: StructureExtension): StructureElement = structure

	private object GetTextVisitor : InlineRenderVisitor {
		override val outputFormat: Format = Format.None
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