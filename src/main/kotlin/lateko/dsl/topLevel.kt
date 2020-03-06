package lateko.dsl

import lateko.dsl.command.makeTitle
import lateko.dsl.header.TexHeaderScope
import lateko.dsl.structure.StructureScope
import lateko.model.Document

fun document(name: String? = null, content: StructureScope.() -> Unit): Document = texDocument(title = name.orEmpty(), header = {}, content = content)

fun texDocument(title: String?, header: TexHeaderScope.() -> Unit, content: StructureScope.() -> Unit): Document {
	val headerBuilder = TexHeaderScope()
	headerBuilder.apply {
		title?.let { title(it) }
		header()
	}
	val contentBuilder = StructureScope()
	contentBuilder.apply {
		title?.let { makeTitle() }
		content()
	}
	return Document(
			name = title,
			header = headerBuilder.build(),
			content = contentBuilder.build()
	)
}
