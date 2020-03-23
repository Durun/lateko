package io.github.durun.lateko.dsl.command

import io.github.durun.lateko.command.tex.BackMatter
import io.github.durun.lateko.command.tex.FrontMatter
import io.github.durun.lateko.command.tex.MainMatter
import io.github.durun.lateko.dsl.structure.StructureScope
import io.github.durun.lateko.model.structure.Structure

fun StructureScope.frontMatter(): Structure = FrontMatter.toLine().adding()
fun StructureScope.mainMatter(): Structure = MainMatter.toLine().adding()
fun StructureScope.backMatter(): Structure = BackMatter.toLine().adding()