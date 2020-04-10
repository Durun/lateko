import io.github.durun.lateko.dsl.chapter
import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.structure.p

val intro = chapter("Introduction") {
	p {
		-"This is a chapter."
	}
}

document("Chapter reference") {
	-intro
	val conclusion = chapter("Conclusion") {
		p {
			-"This is conclusion."
		}
	}
	-conclusion
}