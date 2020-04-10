import io.github.durun.lateko.dsl.chapter
import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.inline.minus
import io.github.durun.lateko.dsl.ref
import io.github.durun.lateko.dsl.structure.p

val intro = chapter("Introduction") {
	p {
		-"This is a chapter."
	}
}
val conclusion = chapter("Conclusion") {
	p {
		-"This is conclusion."
		-"Reference of intro is"
		-intro.ref
	}
	p {
		-("Reference of intro is " - intro.ref - ".")
	}
}
document("Chapter reference") {
	-intro
	-conclusion
}