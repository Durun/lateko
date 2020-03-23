import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.inline.minus
import io.github.durun.lateko.dsl.inline.url
import io.github.durun.lateko.dsl.structure.p

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