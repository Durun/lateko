import lateko.dsl.inline.asMarkdown
import lateko.dsl.document

document {
	p {
		-"text1"
		-"<!-- This is Markdown comment1 -->".asMarkdown()
		-"text2"
		-"<!-- This is Markdown comment2 -->".asMarkdown()
	}
}