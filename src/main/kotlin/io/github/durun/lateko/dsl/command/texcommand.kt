package io.github.durun.lateko.dsl.command

import io.github.durun.lateko.target.tex.ListOfFigures
import io.github.durun.lateko.target.tex.ListOfTables
import io.github.durun.lateko.target.tex.MakeTitle
import io.github.durun.lateko.target.tex.TableOfContents
import io.github.durun.lateko.dsl.structure.StructureScope
import io.github.durun.lateko.model.structure.Structure

fun StructureScope.makeTitle(): Structure = MakeTitle.toLine().adding()
fun StructureScope.tableOfContents(): Structure = TableOfContents.toLine().adding()
fun StructureScope.listOfFigures(): Structure = ListOfFigures.toLine().adding()
fun StructureScope.listOfTables(): Structure = ListOfTables.toLine().adding()