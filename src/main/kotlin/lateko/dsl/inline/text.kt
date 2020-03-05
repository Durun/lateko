package lateko.dsl.inline

import lateko.element.Text


val String.text: Text get() = Text(this)
