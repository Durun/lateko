import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.structure.chapter
import io.github.durun.lateko.dsl.structure.p
import io.github.durun.lateko.dsl.structure.section

document("Paragraph") {
	p { -"A paragraph in Document." }

	chapter("Chapter1") {
		p { -"A paragraph in Chapter." }
		section("Section") {
			p { -"A paragraph in Section." }
			section("SubSection") {
				p { -"A paragraph in SubSection." }
				section("SubSubSection") {
					p { -"A paragraph in SubSubsection." }
				}
			}
		}
	}
}