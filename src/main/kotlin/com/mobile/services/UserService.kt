package com.mobile.services

import com.mobile.models.User
import com.mobile.repository.UserRepository

class UserService (private val userRepository: UserRepository){

    suspend fun findById(id: Long): User {
        return userRepository.getUserById(id) ?: error("Not found user with an id: $id" )
    }

    suspend fun login(email: String, password: String): User?{
        return userRepository.login(email, password)
    }

    suspend fun register(name: String, email: String, password: String): User? {
        return userRepository.addUser(name, email, password)
    }

    suspend fun updateUser(id: Long, name: String, email: String, password: String): Boolean{
        return userRepository.updateUser(id, name, email, password)
    }

    suspend fun deleteUser(id: Long): Boolean{
        return userRepository.deleteUser(id)
    }
}