package lateko.renderer.tex.visitor

import lateko.renderer.common.LineRenderVisitor


internal interface TexLineRenderVisitor :
		TexLineRenderVisitorCore,
		TexListRenderVisitor
// TODO: Add visitors here.

internal interface TexLineRenderVisitorCore : LineRenderVisitor