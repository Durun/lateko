package io.github.durun.lateko.model

import io.github.durun.lateko.model.inline.InlineVisitor
import io.github.durun.lateko.model.line.LineVisitor
import io.github.durun.lateko.model.structure.StructureVisitor

interface DocumentVisitor<R> : StructureVisitor<R>, LineVisitor<R>, InlineVisitor<R>



