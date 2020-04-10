package io.github.durun.lateko.dsl.reference

import io.github.durun.lateko.model.Labeled
import io.github.durun.lateko.model.inline.LazyReference
import io.github.durun.lateko.model.inline.Reference
import io.github.durun.lateko.model.inline.SimpleReference

fun ref(label: String): Reference = SimpleReference(label)
val Labeled.ref: Reference get() = LazyReference(this)