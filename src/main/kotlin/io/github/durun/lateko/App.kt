package io.github.durun.lateko

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import io.github.durun.lateko.app.CompileApp

class App : CliktCommand() {
	override fun run() {}
}

fun main(args: Array<String>) = App().subcommands(
		CompileApp()
).main(args)
