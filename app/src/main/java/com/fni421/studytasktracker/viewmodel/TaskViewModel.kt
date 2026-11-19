package com.fni421.studytasktracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.fni421.studytasktracker.data.AppDatabase
import com.fni421.studytasktracker.data.Task
import com.fni421.studytasktracker.data.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(app: Application) : AndroidViewModel(app) {

    // Repository gets its DAO from the Room database
    private val repo = TaskRepository(AppDatabase.getDatabase(app).taskDao())

    // Turns the database Flow into a StateFlow the screens can observe
    val tasks: StateFlow<List<Task>> = repo.allTasks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTask(title: String, subject: String) {
        viewModelScope.launch { repo.add(Task(title = title, subject = subject)) }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch { repo.update(task) }
    }

    // copy() keeps the other fields and flips only isDone
    fun toggleDone(task: Task) = updateTask(task.copy(isDone = !task.isDone))

    fun deleteTask(task: Task) {
        viewModelScope.launch { repo.delete(task) }
    }

    suspend fun getTask(id: Int): Task? = repo.getTask(id)
}