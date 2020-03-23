package io.github.durun.lateko.dsl.inline

import io.github.durun.lateko.model.inline.Text


val String.text: Text get() = Text(this)
