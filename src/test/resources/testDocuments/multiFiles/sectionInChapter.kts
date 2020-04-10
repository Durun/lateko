import io.github.durun.lateko.dsl.section
import io.github.durun.lateko.dsl.structure.p

section("Section in a chapter.",
		chapterName = "Chapter1") {
	p {
		-"""This is a section in "Chapter1"."""
	}
}