package com.mobile.plugins

import com.mobile.models.Todo
import com.mobile.models.Todos
import com.mobile.models.Users
import io.ktor.server.application.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.jetbrains.exposed.sql.transactions.experimental.*


object DatabaseFactory{
    fun init() {
        val driverClassName = "org.h2.Driver"
        val jdbcURL = "jdbc:h2:mem:test_mem"
        val database = Database.connect(jdbcURL, driverClassName)
        transaction(database) {
            SchemaUtils.create(Users)
            SchemaUtils.create(Todos)
        }
    }

    suspend fun <T> dbQuery(block: suspend() -> T) : T = newSuspendedTransaction(Dispatchers.IO) {block()}
}

