package lateko.model

import lateko.model.inline.InlineVisitor
import lateko.model.structure.StructureVisitor

interface DocumentVisitor<R> : StructureVisitor<R>, InlineVisitor<R>



