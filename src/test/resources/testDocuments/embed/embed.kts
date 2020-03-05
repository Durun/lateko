import lateko.dsl.inline.asMarkdown
import lateko.dsl.document
import lateko.dsl.inline.asTex

document("Embed") {
	p {
		-"text1"
		-"<!-- This is Markdown comment1 -->".asMarkdown()
		-"% This is Tex comment1".asTex()
		-"text2"
		-"% This is Tex comment2".asTex()
		-"<!-- This is Markdown comment2 -->".asMarkdown()
	}
}