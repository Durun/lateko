package lateko.renderer.common

import lateko.model.Document
import lateko.model.inline.*
import lateko.model.structure.*
import lateko.model.structure.StructureComposition.Companion.toComposition

class NameSectionIdVisitor : StructureVisitor<StructureElement> {
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
		val new = Section(
				content = section.content.accept(this),
				name = section.name,
				idName = "${"sub".repeat(sectionNestLevel)}sec:${nameStack.toId()}"
		)
		nameStack.pop()
		sectionNestLevel--
		return new
	}

	override fun visit(chapter: Chapter): StructureElement {
		nameStack.push(chapter.name.getText())
		val new = Chapter(
				content = chapter.content.accept(this),
				name = chapter.name,
				idName = "ch:${nameStack.toId()}"
		)
		nameStack.pop()
		return new
	}

	override fun visit(document: Document): StructureElement {
		return Document(
				name = document.name,
				header = document.header,
				content = document.content.accept(this)
		)
	}

	private object GetTextVisitor : InlineRenderVisitor {
		override fun String.escape(): String {
			return this.replace('#', '＃')
					.replace('&', '＆')
					.replace('%', '％')
					.replace('{', '｛')
					.replace('}', '｝')
			// TODO
		}

		override fun EmbeddedCode.isEnabled(): Boolean = false
		override fun visit(ref: Reference): String = throw IllegalStateException()
		override fun visit(urlText: UrlText): String = urlText.text.rendered
		override fun visit(text: StyledText): String = text.string
	}
}