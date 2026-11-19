package com.fni421.studytasktracker.data

import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {

    // Live list of all tasks, newest first
    val allTasks: Flow<List<Task>> = dao.getAll()

    suspend fun getTask(id: Int): Task? = dao.getById(id)
    suspend fun add(task: Task) = dao.insert(task)
    suspend fun update(task: Task) = dao.update(task)
    suspend fun delete(task: Task) = dao.delete(task)
}