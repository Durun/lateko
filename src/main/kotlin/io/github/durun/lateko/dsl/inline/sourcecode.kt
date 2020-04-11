package io.github.durun.lateko.dsl.inline

import io.github.durun.lateko.model.inline.SourceCode

fun code(title: String, label: String, content: () -> String) = SourceCode(title = title, content = content(), label = label)
fun code(title: String, content: () -> String) = SourceCode(title = title, content = content())