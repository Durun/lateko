package io.github.durun.lateko.dsl.inline

import io.github.durun.lateko.extension.StyledText
import io.github.durun.lateko.model.inline.Text

private val Text.styled: StyledText get() = StyledText(this, emptySet())

private fun StyledText.enabling(style: StyledText.Style) = StyledText(text = text, styles = styles + style)

val StyledText.strong: StyledText get() = enabling(StyledText.Style.Strong)
val StyledText.italic: StyledText get() = enabling(StyledText.Style.Italic)
val StyledText.tt: StyledText get() = enabling(StyledText.Style.Typewriter)
val StyledText.smallCaps: StyledText get() = enabling(StyledText.Style.SmallCaps)

val Text.strong: StyledText get() = styled.strong
val Text.italic: StyledText get() = styled.italic
val Text.tt: StyledText get() = styled.tt
val Text.smallCaps: StyledText get() = styled.smallCaps

val String.strong: StyledText get() = text.strong
val String.italic: StyledText get() = text.italic
val String.tt: StyledText get() = text.tt
val String.smallCaps: StyledText get() = text.smallCaps