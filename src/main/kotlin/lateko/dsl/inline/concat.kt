package lateko.dsl.inline

import lateko.element.InlineComposition
import lateko.element.InlineElement

operator fun InlineElement.minus(other: InlineElement): InlineElement {
	return InlineComposition.of(this, other)
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