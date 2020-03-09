import lateko.dsl.document
import lateko.dsl.inline.strong
import lateko.dsl.structure.chapter
import lateko.dsl.structure.list
import lateko.dsl.structure.p

document("Simple List") {
	chapter("Simple list example") {
		p {
			list {
				-"a"
				-"b"
				-"c"
				-"Strong text".strong
			}
		}
	}
}