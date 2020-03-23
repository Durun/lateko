package io.github.durun.lateko.dsl.inline

import io.github.durun.lateko.model.inline.InlineComposition.Companion.toComposition
import io.github.durun.lateko.model.inline.InlineElement

operator fun InlineElement.minus(other: InlineElement): InlineElement {
	return listOf(this, other).toComposition()
}

operator fun InlineElement.minus(other: String): InlineElement {
	return this - other.text
}

operator fun String.minus(other: InlineElement): InlineElement {
	return this.text - other
}

@Deprecated("Using + is deprecated.", replaceWith = ReplaceWith("this - other", "io.github.durun.lateko.dsl.inline.minus"))
operator fun InlineElement.plus(other: InlineElement): InlineElement = this - other

@Deprecated("Using + is deprecated.", replaceWith = ReplaceWith("this - other", "io.github.durun.lateko.dsl.inline.minus"))
operator fun InlineElement.plus(other: String): InlineElement = this - other
