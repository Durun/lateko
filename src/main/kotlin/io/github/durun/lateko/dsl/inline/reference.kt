package io.github.durun.lateko.dsl.inline

import io.github.durun.lateko.model.inline.LabelReference

fun ref(label: String): LabelReference = LabelReference(label)