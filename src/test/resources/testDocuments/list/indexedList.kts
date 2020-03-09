import lateko.dsl.document
import lateko.dsl.inline.strong
import lateko.dsl.structure.chapter
import lateko.dsl.structure.listIndexed
import lateko.dsl.structure.p

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