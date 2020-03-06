package lateko.dsl

import lateko.element.Composition
import lateko.element.Element

abstract class Builder<E : Element, C : Composition> {
	private val mutableElements: MutableList<E> = mutableListOf()
	protected val elements: List<E> get() = mutableElements

	fun <T : E> T.adding(): T {
		mutableElements += this
		return this
	}

	fun <T : E> T.addingFirst(): T {
		mutableElements.add(index = 0, element = this)
		return this
	}

	abstract fun build(): C
}


@DslMarker
annotation class ScopeMarker