package com.mobile

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.mobile.plugins.*
import org.jetbrains.exposed.sql.Database
import javax.xml.crypto.Data

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    configureSerialization()
    configureKoin()
    configureRouting()
}
