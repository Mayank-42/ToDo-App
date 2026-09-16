package com.example.t0d0app.data.local

import com.example.t0d0app.TaskViewModelFactory

class AppContainer(
    private val database: todoDatabase
) {
    val taskDAO = database.Daoo()

    val repository = todoReposatory(taskDAO)

    val viewModelFactory = TaskViewModelFactory(repository)
}