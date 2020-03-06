package lateko.dsl.structure

import lateko.command.TexCommand
import lateko.element.Structure


fun StructureScope.makeTitle(): Structure = TexCommand("maketitle").toLine().adding()

