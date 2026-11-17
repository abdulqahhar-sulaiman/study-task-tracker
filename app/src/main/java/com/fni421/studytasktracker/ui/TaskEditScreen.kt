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

// PLACEHOLDER: Member 5 (Zainab) replaces everything in this file.
// Keep the function signature exactly as it is (it is part of the shared contract).
// taskId == -1 means "add a new task".
@Composable
fun TaskEditScreen(
    viewModel: TaskViewModel,
    taskId: Int,
    onDone: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Add/Edit screen (placeholder), taskId = $taskId")
        Button(onClick = onDone) { Text("Back") }
    }
}
