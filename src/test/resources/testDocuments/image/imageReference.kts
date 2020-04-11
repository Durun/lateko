import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.inline.image
import io.github.durun.lateko.dsl.reference.ref
import io.github.durun.lateko.dsl.structure.p

val sampleImage = image("Sample image", path = "sample.png")

document("PNG Image Reference") {
	p {
		-sampleImage.ref
		-"is the sample image."
		-sampleImage
	}
}