import io.github.durun.lateko.dsl.chapterOf
import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.structure.p

val intro = chapterOf("Introduction") {
	p {
		-"This is a chapter."
	}
}

document("Chapter reference") {
	-intro
	val conclusion = chapterOf("Conclusion") {
		p {
			-"This is conclusion."
		}
	}
	-conclusion
}