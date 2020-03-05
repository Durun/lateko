import lateko.dsl.document
import lateko.dsl.inline.minus
import lateko.dsl.inline.url

document("TextConcat") {
	p {
		-("a" + "b")
	}
	p {
		-("http://example.com".url - " is a URL.")
	}
	p {
		-("A " - "hyperLink".url("http://example.com") - ".")
	}
}