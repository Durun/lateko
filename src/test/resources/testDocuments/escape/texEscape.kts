import lateko.dsl.document
import lateko.dsl.structure.chapter
import lateko.dsl.structure.p
import lateko.dsl.structure.section

document("TeX Escape") {
	chapter("System special characters") {
		section("Backslash") { p { -"foo\\bar" } }
		section("Sharp") { p { -"foo#bar" } }
		section("Dollar") { p { -"foo\$bar" } }
		section("Percent") { p { -"foo%bar" } }
		section("Ampersand") { p { -"foo&bar" } }
		section("Under bar") { p { -"foo_bar" } }
		section("Braces") {
			p { -"foo{bar" }
			p { -"foo}bar" }
			p { -"bar{foo}bar" }
		}
	}
	chapter("Special characters") {
		section("Bar") { p { -"foo|bar" } }
		section("Asterisk") { p { -"foo*bar" } }
		section("Less") { p { -"foo<bar" } }
		section("Greater") { p { -"foo>bar" } }
		section("Hat") { p { -"foo^bar" } }
		section("Tilde") { p { -"foo~bar" } }
	}
}