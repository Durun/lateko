import lateko.dsl.document
import lateko.dsl.inline.url
import lateko.dsl.structure.chapter
import lateko.dsl.structure.section

document("サンプル") {

	p {
		-"どぅるん".url("https://twitter.com/NegiIgaiNuki")
	}

	chapter("挨拶") {
		section("挨拶") {
			p {
				-"こんにちは。"
				-"はい、こんにちは。"
			}
			section {
				p {
					-"これはsubsection."
				}
			}
			p {
				-"こんばんは。"
				-"いいえ、おはよう御座います。"
			}
		}
	}

	section("フランクな挨拶") {
		p {
			-"やっほー。"
			-"はい、こんにちは。"
		}
		p {
			-"ほーい。"
			-"いいえ、おはよう御座います。"
		}
	}
}
