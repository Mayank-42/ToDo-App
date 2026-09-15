package com.example.t0d0app.data.local

class todoReposatory (
    private val taskDao:taskDAO
){
    val allTasks = taskDao.getAllTasks()

    suspend fun getallMarked(){
        taskDao.getAllMarked()
    }

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

}