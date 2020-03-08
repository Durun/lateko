import lateko.dsl.chapterOf
import lateko.dsl.document
import lateko.dsl.ref
import lateko.dsl.structure.p

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
}
document("Chapter reference") {
	-intro
	-conclusion
}