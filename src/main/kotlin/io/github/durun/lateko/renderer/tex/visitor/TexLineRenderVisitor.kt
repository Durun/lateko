package io.github.durun.lateko.renderer.tex.visitor

import io.github.durun.lateko.renderer.common.LineRenderVisitor


internal interface TexLineRenderVisitor :
		TexLineRenderVisitorCore,
		TexListRenderVisitor
// TODO: Add visitors here.

internal interface TexLineRenderVisitorCore : LineRenderVisitor