import lateko.dsl.chapterOf
import lateko.dsl.document
import lateko.dsl.structure.p

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