package com.indranil.todoapp

import android.app.Application
import androidx.room.Room
import com.indranil.todoapp.db.ToDoDatabase

class MainApplication: Application() {
    companion object {
        lateinit var toDoDatabase : ToDoDatabase
    }
    override fun onCreate() {
        super.onCreate()
        toDoDatabase = Room.databaseBuilder(
            applicationContext,
            ToDoDatabase::class.java,
            ToDoDatabase.NAME
        ).build()
    }

}