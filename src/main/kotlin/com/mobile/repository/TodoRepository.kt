package com.mobile.repository

import com.mobile.models.Todo
import com.mobile.models.Todos
import com.mobile.services.UserService
import org.jetbrains.exposed.sql.ResultRow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface TodoRepository {
    fun getAll() : MutableList<Todo>
    fun findTodo(id: Long) : Todo?
    fun addTodo(todo: Todo)
}

class TodoRepositoryImpl : TodoRepository, KoinComponent{
    private val _todos = mutableListOf<Todo>()
    private val userService by inject<UserService>()

    private suspend fun resultRowToTodo(row: ResultRow) : Todo {
        val todo = Todo(id = row[Todos.id],
            name = row[Todos.name],
            creationDate = row[Todos.creationDate],
            user = userService.findById(row[Todos.user]))
        todo.description = row[Todos.description]
        todo.endDate = row[Todos.endDate]
        return todo
    }

    override fun getAll(): MutableList<Todo> {
        return _todos;
    }

    override fun findTodo(id: Long): Todo? {
        return _todos.firstOrNull{it.id == id}
    }

    override fun addTodo(todo: Todo) {
        _todos.add(todo)
    }
}