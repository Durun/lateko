package lateko.model

import lateko.model.inline.InlineVisitor
import lateko.model.line.LineVisitor
import lateko.model.structure.StructureVisitor

interface DocumentVisitor<R> : StructureVisitor<R>, LineVisitor<R>, InlineVisitor<R>



