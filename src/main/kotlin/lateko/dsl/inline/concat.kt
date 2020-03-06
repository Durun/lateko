package lateko.dsl.inline

import lateko.model.InlineComposition.Companion.toComposition
import lateko.model.InlineElement

operator fun InlineElement.minus(other: InlineElement): InlineElement {
	return listOf(this, other).toComposition()
}

operator fun InlineElement.minus(other: String): InlineElement {
	return this - other.text
}

operator fun String.minus(other: InlineElement): InlineElement {
	return this.text - other
}

@Deprecated("Using + is deprecated.", replaceWith = ReplaceWith("this - other", "lateko.dsl.inline.minus"))
operator fun InlineElement.plus(other: InlineElement): InlineElement = this - other

@Deprecated("Using + is deprecated.", replaceWith = ReplaceWith("this - other", "lateko.dsl.inline.minus"))
operator fun InlineElement.plus(other: String): InlineElement = this - other
