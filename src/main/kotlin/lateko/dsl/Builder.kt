package lateko.dsl

import lateko.element.Composition
import lateko.element.Element

abstract class Builder<E : Element, C : Composition> {
	protected val elements: MutableList<E> = mutableListOf()
	fun <T : E> T.adding(): T {
		elements += this
		return this
	}

	abstract fun build(): C
}


@DslMarker
annotation class ScopeMarker