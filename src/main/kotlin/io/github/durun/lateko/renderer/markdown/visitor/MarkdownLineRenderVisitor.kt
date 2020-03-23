package io.github.durun.lateko.renderer.markdown.visitor

import io.github.durun.lateko.renderer.common.LineRenderVisitor


internal interface MarkdownLineRenderVisitor :
		MarkdownLineRenderVisitorCore,
		MarkdownListRenderVisitor
// TODO: Add visitors here.

internal interface MarkdownLineRenderVisitorCore : LineRenderVisitor