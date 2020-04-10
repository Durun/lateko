import io.github.durun.lateko.dsl.chapterOf
import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.inline.minus
import io.github.durun.lateko.dsl.ref
import io.github.durun.lateko.dsl.structure.p

val intro = chapterOf("Introduction") {
	p {
		-"This is a chapter."
	}
}
val conclusion = chapterOf("Conclusion") {
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