package lateko.visitor

interface DocumentRenderVisitor : StructureRenderVisitor, InlineRenderVisitor
interface StructureRenderVisitor : StructureVisitor<String>
interface InlineRenderVisitor : InlineVisitor<String>