package lateko.dsl.command

import lateko.command.TexCommand
import lateko.dsl.structure.StructureScope
import lateko.element.Structure

fun StructureScope.makeTitle(): Structure = TexCommand("maketitle").toLine().adding()
