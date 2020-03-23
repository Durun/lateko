import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.structure.chapter
import io.github.durun.lateko.dsl.structure.p
import io.github.durun.lateko.dsl.structure.section

document("# Markdown Escapes #") {
	chapter("## chapter ##") {
		p {
			-"\\ <- this is backslash"
		}
		p {
			-"*asterisk*"
			-",and**asterisks**"
		}
		section("#") {
			section("#") {
				p {
					-"__under_bars__"
				}
				p {
					-"` <- this is `backquote`"
				}
			}
		}
	}
}