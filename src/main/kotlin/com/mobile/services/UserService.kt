package com.mobile.services

import com.mobile.models.User
import com.mobile.repository.TodoRepository
import com.mobile.repository.UserRepository
import io.ktor.server.engine.*

class UserService (private val userRepository: UserRepository){

    suspend fun findById(id: Long): User {
        return userRepository.getUserById(id) ?: error("Not found user with an id: $id" )
    }
}