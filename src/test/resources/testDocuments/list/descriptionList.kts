import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.structure.chapter
import io.github.durun.lateko.dsl.structure.listDescription
import io.github.durun.lateko.dsl.structure.p

document("Description List") {
	chapter("Description list example") {
		p {
			-"With linebreak"
			listDescription {
				item("titleA", "descriptionA")
				item("titleB", "descriptionB")
				item("titleC", "descriptionC")
			}
			-"And without linebreak"
			listDescription(lineBreak = false) {
				item("titleA", "descriptionA")
				item("titleB", "descriptionB")
				item("titleC", "descriptionC")
			}
			-"End."
		}
	}
}