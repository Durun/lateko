import lateko.dsl.document

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