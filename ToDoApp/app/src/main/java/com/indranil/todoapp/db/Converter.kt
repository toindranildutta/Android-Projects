package com.indranil.todoapp.db

import androidx.room.TypeConverter
import java.util.Date

class Converter {
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }
    @TypeConverter
    fun toDate(timestamp: Long): Date {
        return Date(timestamp)
    }

}