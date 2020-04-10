package io.github.durun.lib.path

import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

fun Path.mapName(transform: (File) -> String): Path {
	val name = this.toFile().let(transform)
	val parent: Path = runCatching { this.toAbsolutePath().parent }
			.getOrDefault(Paths.get("/"))
	return parent.resolve(name)
}