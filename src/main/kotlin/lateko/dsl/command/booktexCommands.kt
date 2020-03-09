package lateko.dsl.command

import lateko.command.tex.BackMatter
import lateko.command.tex.FrontMatter
import lateko.command.tex.MainMatter
import lateko.dsl.structure.StructureScope
import lateko.model.structure.Structure

fun StructureScope.frontMatter(): Structure = FrontMatter.toLine().adding()
fun StructureScope.mainMatter(): Structure = MainMatter.toLine().adding()
fun StructureScope.backMatter(): Structure = BackMatter.toLine().adding()