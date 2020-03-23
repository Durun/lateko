package io.github.durun.lateko.dsl.inline

import io.github.durun.lateko.model.inline.InlineElement
import io.github.durun.lateko.model.inline.StyledText
import io.github.durun.lateko.model.inline.UrlText


fun InlineElement.url(url: String): UrlText = UrlText(url = url, text = this)
fun String.url(url: String): UrlText = this.text.url(url)
val String.url: UrlText get() = this.text.url(this)
val StyledText.url: UrlText get() = url(url = string)