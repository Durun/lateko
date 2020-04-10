import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.structure.chapter
import io.github.durun.lateko.dsl.structure.p
import io.github.durun.lateko.dsl.structure.section

document("Custom Label") {
	chapter("Chapter1", label = "chapterone") {
		p {
			-"This is Chapter1."
		}
		section("Section1", label = "sectionone") {
			p {
				-"This is Section1."
			}
		}
	}
}