package com.mobile.routes

import com.mobile.services.TodoService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.todoRouting(){

    val service by inject<TodoService>()

    route("/api/todo"){
        get{
            call.respond(service.getAllTodo())
        }
    }
}