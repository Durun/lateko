package lateko.dsl.command

import lateko.command.TexCommand
import lateko.dsl.structure.StructureScope
import lateko.model.structure.Structure

fun StructureScope.makeTitle(): Structure = TexCommand("maketitle").toLine().adding()
