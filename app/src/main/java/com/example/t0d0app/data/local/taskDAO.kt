package com.example.t0d0app.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface taskDAO {

    @Insert
    suspend fun insert(task:Task)
    @Delete
    suspend fun delete(task:Task)
    @Update
    suspend fun update(task:Task)

    @Query("SELECT * FROM task")
    fun getAllTasks(): Flow<List<Task>>

    @Query("DELETE FROM Task WHERE isMarked=1")
    suspend fun getAllMarked()

}