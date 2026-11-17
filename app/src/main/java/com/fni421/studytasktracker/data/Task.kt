package com.fni421.studytasktracker.data

// PLACEHOLDER: Member 1 (Arqam) adds the Room annotations
// (@Entity(tableName = "tasks") and @PrimaryKey(autoGenerate = true)).
// Do not rename or remove any field: everyone depends on this class.
data class Task(
    val id: Int = 0,
    val title: String,
    val subject: String,
    val isDone: Boolean = false
)
