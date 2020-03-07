package lateko.dsl.command

import lateko.command.tex.MakeTitle
import lateko.dsl.structure.StructureScope
import lateko.model.structure.Structure

fun StructureScope.makeTitle(): Structure = MakeTitle.toLine().adding()
