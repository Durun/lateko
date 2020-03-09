package lateko.renderer.markdown.visitor

import lateko.renderer.common.LineRenderVisitor


internal interface MarkdownLineRenderVisitor :
		MarkdownLineRenderVisitorCore,
		MarkdownListRenderVisitor
// TODO: Add visitors here.

internal interface MarkdownLineRenderVisitorCore : LineRenderVisitor