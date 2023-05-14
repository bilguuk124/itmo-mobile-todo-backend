package com.mobile.repository

import com.mobile.models.User
import com.mobile.models.Users
import com.mobile.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.koin.core.component.KoinComponent

interface UserRepository{
    suspend fun getAll() : List<User>
    suspend fun getUserById(id: Long) : User?
    suspend fun addUser(user: User)
}

class UserRepositoryImpl : UserRepository, KoinComponent{
    private val users = ArrayList<User>();

    private fun resultRowToUser(row: ResultRow): User {
        return User(
            id = row[Users.id], name = row[Users.name], email = row[Users.email], password = row[Users.password]
        )
    }

    override suspend fun getAll(): List<User> {
       return dbQuery { Users.selectAll().map(::resultRowToUser) }
    }

    override suspend fun getUserById(id: Long): User? {
        return dbQuery{
            Users
            .select(Users.id eq id)
            .map(::resultRowToUser)
            .singleOrNull()
        }
    }

    override suspend fun addUser(user: User) {
        TODO("Not yet implemented")
    }

}