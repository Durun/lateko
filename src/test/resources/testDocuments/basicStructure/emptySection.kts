import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.structure.chapter
import io.github.durun.lateko.dsl.structure.section

document("emptySection") {
	chapter("Chapter") {
		section("Section") { }
	}
}