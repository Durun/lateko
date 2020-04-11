import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.inline.code
import io.github.durun.lateko.dsl.reference.ref
import io.github.durun.lateko.dsl.structure.p

document("Direct Embedding Soucecode") {
	val kotlinSample = code("Kotlin Sample") {
		// language=Kotlin
		"""
			fun main() {
				println("Hello, world!")
			}
		""".trimIndent()
	}

	p {
		-kotlinSample.ref
		-"is the sample sourcecode."
		- kotlinSample
	}
}