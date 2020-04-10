import io.github.durun.lateko.dsl.document
import io.github.durun.lateko.dsl.inline.url
import io.github.durun.lateko.dsl.structure.chapter
import io.github.durun.lateko.dsl.structure.p
import io.github.durun.lateko.dsl.structure.section

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
			section("サブセクション") {
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

	chapter("フランクな挨拶") {
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
