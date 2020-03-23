package io.github.durun.lateko.dsl

import io.github.durun.lateko.model.Composition
import io.github.durun.lateko.model.Element

@DslMarker
annotation class ScopeMarker

@ScopeMarker
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

	operator fun <T : E> T.unaryMinus(): T = this.adding()
	abstract fun build(): Composition<E>
}

