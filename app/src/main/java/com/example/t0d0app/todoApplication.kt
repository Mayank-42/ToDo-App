package com.example.t0d0app

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.t0d0app.data.local.AppContainer
import com.example.t0d0app.data.local.todoDatabase
import kotlin.jvm.java

//class todoApplication : Application() {
//
//    lateinit var container: AppContainer
//
//    val database: todoDatabase by lazy {
//        Room.databaseBuilder(
//            applicationContext,
//            todoDatabase::class.java,
//            "todo_DataBase"
//        )
//            .addCallback(object : RoomDatabase.Callback() {
//
//                override fun onCreate(db: SupportSQLiteDatabase) {
//                    super.onCreate(db)
//
//                    db.execSQL(
//                        "INSERT INTO Task (task, isMarked) VALUES ('SWIPE TO DELETE', 0)"
//                    )
//
//                    db.execSQL(
//                        "INSERT INTO Task (task, isMarked) VALUES ('Click on add button', 0)"
//                    )
//                }
//            })
//            .build()
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//
//        container = AppContainer(database)
//    }
//}
//class todoApplication : Application() {
//
//    lateinit var database: todoDatabase
//    lateinit var container: AppContainer
//
//    override fun onCreate() {
//        super.onCreate()
//
//        database = Room.databaseBuilder(
//            applicationContext,
//            todoDatabase::class.java,
//            "todo_database"
//        ).build()
//
//        container = AppContainer(database)
//    }
//}
class todoApplication : Application() {

    lateinit var database: todoDatabase
    lateinit var   container: AppContainer

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            todoDatabase::class.java,
            "todo_database"
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

        container = AppContainer(database)
    }
}