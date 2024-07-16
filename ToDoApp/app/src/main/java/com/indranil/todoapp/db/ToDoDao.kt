package com.indranil.todoapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.indranil.todoapp.ToDo

@Dao
interface ToDoDao {
    @Query("SELECT * FROM ToDo")
    fun getAllToDo(): LiveData<List<ToDo>>
    @Upsert
    fun addToDo(toDo: ToDo)
    @Query("DELETE FROM ToDo WHERE id = :id")
    fun deleteToDo(id: Int)

}