import io.github.durun.lateko.dsl.sectionOf
import io.github.durun.lateko.dsl.structure.p

sectionOf("Section in a chapter.",
		chapterName = "Chapter1") {
	p {
		-"""This is a section in "Chapter1"."""
	}
}