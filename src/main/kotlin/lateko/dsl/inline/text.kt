package lateko.dsl.inline

import lateko.element.*


val String.text: Text get() = Text(this)
