package lateko.dsl.inline

import lateko.model.Text


val String.text: Text get() = Text(this)
