package com.mobile.controller

import com.mobile.services.TodoService
import com.mobile.services.UserService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.userRouting(){

    val todoService by inject<TodoService>()
    val userService by inject<UserService>()

    route("/api/todo"){
        get{
        }
    }
}