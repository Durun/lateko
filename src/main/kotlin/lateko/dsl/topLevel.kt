package lateko.dsl

import lateko.element.Document
import lateko.element.StructureComposition

fun document(name: String? = null, content: StructureScope.() -> Unit): Document {
	val composition = StructureComposition.of(content)
	return Document(name = name, content = composition)
}