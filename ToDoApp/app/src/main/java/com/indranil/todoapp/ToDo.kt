package com.indranil.todoapp

import java.time.Instant
import java.util.Date

data class ToDo(
    var id: Int,
    var title: String,
    var createdAt: Date
)

fun getListOfToDo(): List<ToDo> {
    return listOf<ToDo>(
        ToDo(1, "Do something", Date.from(Instant.now())),
        ToDo(2, "Do something else", Date.from(Instant.now())),
        ToDo(3, "Do something else 2", Date.from(Instant.now())),
        ToDo(4, "Do something else 3", Date.from(Instant.now())),
    )

}