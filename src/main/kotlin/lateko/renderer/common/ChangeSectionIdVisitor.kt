package lateko.renderer.common

import lateko.model.Document
import lateko.model.inline.*
import lateko.model.structure.*
import lateko.model.structure.StructureComposition.Companion.toComposition

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

	private fun MutableList<String>.toId() = this.joinToString("/")

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
		override fun String.escape(): String {
			return this.replace('#', '＃')
					.replace('&', '＆')
					.replace('%', '％')
					.replace('{', '｛')
					.replace('}', '｝')
					.replace(" ", "")
			// TODO
		}

		override fun EmbeddedCode.isEnabled(): Boolean = false
		override fun visit(ref: Reference): String = throw IllegalStateException()
		override fun visit(urlText: UrlText): String = urlText.text.rendered
		override fun visit(text: StyledText): String = text.string
	}
}