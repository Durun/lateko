import lateko.dsl.document
import lateko.dsl.structure.chapter
import lateko.dsl.structure.p

document("This is the title breaking line\nhere") {
	chapter("Example") {
		p {
			-"This is a statement breaking line\nhere."
		}
	}
}