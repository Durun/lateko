import lateko.dsl.document
import lateko.dsl.structure.chapter
import lateko.dsl.structure.section

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