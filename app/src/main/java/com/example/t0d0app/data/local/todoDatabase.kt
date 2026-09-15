package com.example.t0d0app.data.local


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room3.RoomDatabase


@Database(
    entities=[Task::class],
    version=1
)
abstract class todoDatabase: RoomDatabase() {

    abstract fun Daoo(): taskDAO
}
