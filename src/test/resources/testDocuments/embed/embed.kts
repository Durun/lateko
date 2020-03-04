import lateko.dsl.document

document {
	p {
		-"text1"
		-"<!-- This is Markdown comment1 -->".asMarkdown()
		-"text2"
		markdown("<!-- This is Markdown comment2 -->")
	}
}