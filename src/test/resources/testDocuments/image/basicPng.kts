import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.inline.image
import io.github.durun.lateko.dsl.structure.p

document("Basic PNG Insertion") {
	p {
		-image("Sample image", path = "sample.png")
	}
}