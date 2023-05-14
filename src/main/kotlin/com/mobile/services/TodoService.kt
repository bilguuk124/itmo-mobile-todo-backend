package com.mobile.services

import com.mobile.models.Todo
import com.mobile.models.User
import com.mobile.repository.TodoRepository
import java.time.LocalDate

class TodoService(private val todoRepository: TodoRepository) {

    fun getAllTodo(): MutableList<Todo>{
        return todoRepository.getAll()
    }

    fun getTodoById(id: Long) : Todo{
        return todoRepository.findTodo(id) ?: error("Can't find todo with id: $id")
    }

    fun addTodo(name: String, user: User){
        val todo = Todo(0, name, LocalDate.now(), user)
        todoRepository.addTodo(todo)
    }



}