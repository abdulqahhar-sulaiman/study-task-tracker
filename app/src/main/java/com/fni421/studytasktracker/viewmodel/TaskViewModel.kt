package com.fni421.studytasktracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.fni421.studytasktracker.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

// PLACEHOLDER: Member 2 (Kaamelah) replaces the body so it uses the Repository/Room.
// This in-memory version lets Members 4 and 5 build and test their screens right now.
// Keep the public members exactly as they are (shared contract).
class TaskViewModel(app: Application) : AndroidViewModel(app) {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private var nextId = 1

    fun addTask(title: String, subject: String) {
        _tasks.update { it + Task(id = nextId++, title = title, subject = subject) }
    }

    fun updateTask(task: Task) {
        _tasks.update { list -> list.map { if (it.id == task.id) task else it } }
    }

    fun toggleDone(task: Task) = updateTask(task.copy(isDone = !task.isDone))

    fun deleteTask(task: Task) {
        _tasks.update { list -> list.filter { it.id != task.id } }
    }

    suspend fun getTask(id: Int): Task? = _tasks.value.find { it.id == id }
}
