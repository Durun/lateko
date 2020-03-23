import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.structure.chapter
import io.github.durun.lateko.dsl.structure.p

document("This is the title breaking line\nhere") {
	chapter("Example") {
		p {
			-"This is a statement breaking line\nhere."
		}
	}
}