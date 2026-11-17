package com.fni421.studytasktracker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fni421.studytasktracker.viewmodel.TaskViewModel

// PLACEHOLDER: Member 4 (Alamin) replaces everything in this file.
// Keep the function signature exactly as it is (it is part of the shared contract).
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    onAddClick: () -> Unit,
    onEditClick: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Task List (placeholder)")
        Button(onClick = onAddClick) { Text("Add task") }
    }
}
