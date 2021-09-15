package me.kaique.application

import me.kaique.application.web.AuthorizationEntryPoint

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        AuthorizationEntryPoint.init()
    }
}