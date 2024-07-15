package com.indranil.todoapp

import java.time.Instant
import java.util.Date

object ToDoManager {
    private val toDoList = mutableListOf<ToDo>()

    fun getAllToDo(): List<ToDo> {

        return toDoList
    }

    fun addToDo(title: String) {
        toDoList.add(ToDo(System.currentTimeMillis().toInt(), title, Date.from(Instant.now())))
    }

    fun deleteToDo(id: Int) {
        toDoList.removeIf { it.id == id }
    }
}