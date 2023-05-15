package com.mobile.controller

import com.mobile.dto.requests.RegisterCredentials
import com.mobile.security.hashing.HashingService
import com.mobile.security.token.TokenService
import com.mobile.services.TodoService
import com.mobile.services.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.userRouting(){

    // val todoService by inject<TodoService>()
    val userService by inject<UserService>()
    val hashingService by inject<HashingService> ()
    // val tokenService by inject<TokenService> ()

    route("/api"){
        get("/login"){

        }
        post("/register"){
            val request = kotlin.runCatching { call.receiveNullable<RegisterCredentials>() }.getOrNull() ?:
                            kotlin.run {
                                call.respond(HttpStatusCode.BadRequest)
                                return@post
                            }
            val areFieldBlank = request.email.isBlank() || request.password.isBlank()
            val isPasswordShort = request.password.length < 4

            if (userService.getUserByEmail(request.email) != null){
                call.respond(HttpStatusCode.Conflict, "Email is already registered!")
                return@post
            }

            if(areFieldBlank || isPasswordShort){
                call.respond(HttpStatusCode.Conflict, "Bad request")
                return@post
            }

            val saltedHash = hashingService.generateSaltedHash(request.password)
            val wasAcknowledged = userService.register(request.name, request.email, request.password, saltedHash.salt)
            if (wasAcknowledged == null){
                call.respond(HttpStatusCode.Conflict, "Failed to register")
                return@post
            }
            else{
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}