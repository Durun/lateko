package lateko.model.line

interface LineVisitor<R> {
	// Core
	fun visit(lines: LineComposition): R
	fun visit(line: Line): R

	// List
	fun visit(list: ItemList): R
	fun visit(item: SimpleItem): R
	fun visit(item: IndexedItem): R
	fun visit(item: DescriptionItem): R
}