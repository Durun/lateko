package lateko.dsl.inline

import lateko.model.inline.Text


val String.text: Text get() = Text(this)
