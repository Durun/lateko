package io.github.durun.lateko.dsl.inline

import io.github.durun.lateko.model.inline.Reference
import io.github.durun.lateko.model.inline.SimpleReference

fun ref(label: String): Reference = SimpleReference(label)