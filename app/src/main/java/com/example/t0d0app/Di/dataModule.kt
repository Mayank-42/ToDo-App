package com.example.t0d0app.Di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.t0d0app.data.local.taskDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.t0d0app.data.local.todoDatabase

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): todoDatabase {

        return Room.databaseBuilder(
            context,
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
    }

    @Provides
    @Singleton
    fun providetodoRepo(
         database: todoDatabase
    ):taskDAO  {
        return database.Daoo()
    }

}