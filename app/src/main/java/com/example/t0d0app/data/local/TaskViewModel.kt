package com.example.t0d0app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.t0d0app.data.local.Task
import com.example.t0d0app.data.local.todoReposatory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repository: todoReposatory
) : ViewModel() {

    val allTasks: Flow<List<Task>> = repository.allTasks


    fun getallMarked(){
        viewModelScope.launch{
            repository.getallMarked()
        }
    }

    fun insert(task: Task) {
        viewModelScope.launch {
            repository.insert(task)
        }
    }

    fun update(task: Task) {
        viewModelScope.launch {
            repository.update(task)
        }
    }
    fun delete(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }
}

class TaskViewModelFactory(
    private val repository: todoReposatory
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}