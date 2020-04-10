package io.github.durun.lateko.dsl.inline

import io.github.durun.lateko.model.inline.Image

fun image(title: String, path: String): Image = Image(title = title, path = path)