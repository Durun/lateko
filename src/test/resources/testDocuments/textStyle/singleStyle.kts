import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.inline.*
import io.github.durun.lateko.dsl.structure.p

document("Single style") {
	p {
		-"This is plain text."
	}
	p {
		-"This is strong style.".strong
	}
	p {
		-"This is italic style.".italic
	}
	p {
		-"This is typewriter style.".tt
	}
	p {
		-"This is SmallCaps style.".smallCaps
	}
	p {
		-"That is url text→"
		-"example.com".url
	}
	p {
		-"This is hyperlink text.".url("example.com")
	}
}