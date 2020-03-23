import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.inline.strong
import io.github.durun.lateko.dsl.structure.chapter
import io.github.durun.lateko.dsl.structure.listIndexed
import io.github.durun.lateko.dsl.structure.p

document("Indexed List") {
	chapter("Indexed list example") {
		p {
			listIndexed {
				-"a"
				-"b"
				-"c"
				-"Strong text".strong
			}
			-"End."
		}
	}
}