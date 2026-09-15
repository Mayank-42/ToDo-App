package com.example.t0d0app.data.local


import androidx.compose.runtime.MutableState
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate=true)
    var id:Int=0,
    var task: String,
    var isMarked:Boolean=false
) {
}