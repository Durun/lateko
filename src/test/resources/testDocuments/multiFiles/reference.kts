import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.reference.ref
import io.github.durun.lateko.dsl.structure.p

document("Reference in separated file") {
	p {
		-"This is a label-reference. ->"
		-ref("label1")
	}
}