import lateko.dsl.structure.DocumentScope
import lateko.dsl.texDocument

fun thesisDocument(title: String, author: String, content: DocumentScope.() -> Unit) = texDocument(title = title, content = content, header = {
	documentClass("jsbook", "a4paper,11pt,oneside,openany,report")
	usePackage("geometry", "left=30mm,right=30mm,top=25mm,bottom=25mm")
	usePackage("graphicx", "dvipdfmx")
	usePackage("cite", "nobreak")
	usePackage("xspace")
	usePackage(
			"hyperref",
			"a4paper,dvipdfmx,pdfdisplaydoctitle=true",
			"bookmarks=true,bookmarksnumbered=true,bookmarkstype=toc,bookmarksopen=true",
			"pdftitle={$title}",
			"pdfauthor={$author}"
	)
	usePackage("pxjahyper")
})

thesisDocument("Sample Thesis", "me") {

}