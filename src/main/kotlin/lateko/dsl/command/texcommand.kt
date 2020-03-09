package lateko.dsl.command

import lateko.command.tex.ListOfFigures
import lateko.command.tex.ListOfTables
import lateko.command.tex.MakeTitle
import lateko.command.tex.TableOfContents
import lateko.dsl.structure.StructureScope
import lateko.model.structure.Structure

fun StructureScope.makeTitle(): Structure = MakeTitle.toLine().adding()
fun StructureScope.tableOfContents(): Structure = TableOfContents.toLine().adding()
fun StructureScope.listOfFigures(): Structure = ListOfFigures.toLine().adding()
fun StructureScope.listOfTables(): Structure = ListOfTables.toLine().adding()