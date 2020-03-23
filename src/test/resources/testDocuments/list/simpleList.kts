import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.inline.strong
import io.github.durun.lateko.dsl.structure.chapter
import io.github.durun.lateko.dsl.structure.list
import io.github.durun.lateko.dsl.structure.p

document("Simple List") {
	chapter("Simple list example") {
		p {
			list {
				-"a"
				-"b"
				-"c"
				-"Strong text".strong
			}
			-"End."
		}
	}
}