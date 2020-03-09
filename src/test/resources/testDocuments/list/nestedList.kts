import lateko.dsl.document
import lateko.dsl.structure.*

document("Nested List") {
	chapter("Nested list example") {
		p {
			-"Simple list."
			list {
				-"a"
				listIndexed {
					-"a-1"
					list {
						-"a-1-i"
						-"a-1-ii"
					}
					-"a-2"
				}
				-"b"
				-"c"
				listDescription {
					item("A", "descriptionA")
					item("B", "descriptionB")
					item("C", "descriptionC")
				}
			}
		}
	}

	chapter("Complicated example") {
		p {
			listDescription {
				item("a", "descriptionA")
				list {
					-"a"
					-"b"
					-"c"
				}
				item("b", "descriptionB")
				listDescription {
					item("A", "descriptionA")
					item("B", "descriptionB")
					item("C", "descriptionC")
				}
				item("c", "descriptionC")
				listIndexed {
					-"a"
					-"b"
					-"c"
				}
			}
		}
	}
}