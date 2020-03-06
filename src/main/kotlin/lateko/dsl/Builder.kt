package lateko.dsl

import lateko.model.Composition
import lateko.model.Element

abstract class Builder<E : Element> {
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

	abstract fun build(): Composition<E>
}


@DslMarker
annotation class ScopeMarker