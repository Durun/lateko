import lateko.dsl.document
import lateko.dsl.inline.italic
import lateko.dsl.inline.strong
import lateko.dsl.inline.url
import lateko.dsl.structure.p

document("Single style") {
	p {
		-"This is plain text."
	}
	p {
		-"This is strong and italic style.".strong.italic
	}
	p {
		-"This is italic and strong style.".strong.italic
	}
	p {
		-"That is strong url text→"
		-"example.com".strong.url
	}
	p {
		-"This is strong hyperlink text.".strong.url("example.com")
	}
	p {
		-"That is strong and italic url text→"
		-"example.com".strong.italic.url
	}
	p {
		-"This is strong and italic hyperlink text.".strong.italic.url("example.com")
	}
}