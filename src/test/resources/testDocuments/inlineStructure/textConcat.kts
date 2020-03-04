import lateko.dsl.document
import lateko.dsl.minus
import lateko.dsl.url

document {
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