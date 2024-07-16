package com.indranil.todoapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.indranil.todoapp.ToDo

@Database(entities = [ToDo::class], version = 1)
@TypeConverters(Converter::class)
abstract class ToDoDatabase : RoomDatabase() {
    companion object {
        const val NAME = "ToDo_DB"
    }
    abstract fun getToDoDao(): ToDoDao

}