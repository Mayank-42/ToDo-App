package com.example.t0d0app

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.t0d0app.data.local.todoDatabase

class todoApplication: Application() {

//    override fun onCreate() {
//        super.onCreate()
     val database: todoDatabase by lazy {
         Room.databaseBuilder(
            applicationContext,
            todoDatabase::class.java,
            "todo_DataBase"
        )
             .addCallback(object : RoomDatabase.Callback() {

                 override fun onCreate(db: SupportSQLiteDatabase) {
                     super.onCreate(db)

                     db.execSQL(
                         "INSERT INTO Task (task, isMarked) VALUES ('SWIPE TO DELETE', 0)"
                     )
                     db.execSQL(
                         "INSERT INTO Task (task, isMarked) VALUES ('Click on add button', 0)"
                     )
                 }
             })
             .build()
    }
}