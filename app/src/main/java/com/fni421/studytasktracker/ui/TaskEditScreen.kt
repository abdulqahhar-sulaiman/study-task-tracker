package com.fni421.studytasktracker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fni421.studytasktracker.viewmodel.TaskViewModel
import com.fni421.studytasktracker.data.Task

@androidx.compose.runtime.Composable
fun TaskEditScreen(
    viewModel: TaskViewModel,
    taskId: Int,
    onDone: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var isDone by remember { mutableStateOf(false) }
    var currentTask by remember { mutableStateOf<Task?>(null) }

    // If editing an existing task, load its information
    LaunchedEffect(taskId) {
        if (taskId != -1) {
            val task = viewModel.getTask(taskId)
            currentTask = task

            if (task != null) {
                title = task.title
                subject = task.subject
                isDone = task.isDone
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = if (taskId == -1) "Add Task" else "Edit Task"
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Task Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = subject,
            onValueChange = { subject = it },
            label = { Text("Subject") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = isDone,
                onCheckedChange = { isDone = it }
            )

            Text(
                text = "Completed"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (taskId == -1) {
                    viewModel.addTask(
                        title = title,
                        subject = subject
                    )
                }  else {
            val task = currentTask

            if (task != null) {
                viewModel.updateTask(
                    task.copy(
                        title = title,
                        subject = subject,
                        isDone = isDone
                    )
                )
            }
                }

                onDone()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onDone,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}