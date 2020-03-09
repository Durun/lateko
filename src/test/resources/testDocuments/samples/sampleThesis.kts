import lateko.dsl.command.*
import lateko.dsl.structure.DocumentScope
import lateko.dsl.structure.chapter
import lateko.dsl.structure.p
import lateko.dsl.texDocument

fun thesisDocument(title: String, author: String,
				   frontContent: DocumentScope.() -> Unit,
				   backContent: DocumentScope.() -> Unit,
				   mainContent: DocumentScope.() -> Unit
) = texDocument(
		title = title,
		autoMakeTitle = false,
		header = {
			documentClass("jsbook", "a4paper,11pt,oneside,openany,report")
			usePackage("geometry", "left=30mm,right=30mm,top=25mm,bottom=25mm")
			usePackage("graphicx", "dvipdfmx")
			usePackage("cite", "nobreak")
			usePackage("xspace")
			usePackage("hyperref",
					"a4paper,dvipdfmx,pdfdisplaydoctitle=true",
					"bookmarks=true,bookmarksnumbered=true,bookmarkstype=toc,bookmarksopen=true",
					"pdftitle={$title}",
					"pdfauthor={$author}"
			)
			usePackage("pxjahyper")
			requirePackage("nameref")
			requirePackage("titleref")
		},
		content = {
			frontMatter()
			makeTitle()
			frontContent()
			tableOfContents()
			listOfFigures()
			listOfTables()
			mainMatter()
			mainContent()
			backMatter()
			backContent()
		})

thesisDocument("Sample Thesis", "me",
		frontContent = {
			chapter("概要") {
				p {
					-"これは論文形式のサンプルです。"
				}
			}
		},
		backContent = {
			chapter("謝辞") {
				p {
					-"これは謝辞です。"
				}
			}
		}) {
	chapter("はじめに") {
		p {
			-"これはサンプルです。"
		}
	}
	chapter("おわりに") {
		p {
			-"以上、サンプルでした。"
		}
	}
}