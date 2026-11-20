
package com.fni421.studytasktracker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.fni421.studytasktracker.data.Task
import com.fni421.studytasktracker.viewmodel.TaskViewModel

@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    onAddClick: () -> Unit,
    onEditClick: (Int) -> Unit
) {
    val tasks by viewModel.tasks.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "My Study Tasks",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onAddClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add New Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (tasks.isEmpty()) {
            Text(
                text = "No tasks yet. Add your first study task!",
                style = MaterialTheme.typography.bodyLarge
            )
        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = tasks,
                    key = { task -> task.id }
                ) { task ->

                    TaskItem(
                        task = task,
                        onToggle = { viewModel.toggleDone(task) },
                        onEdit = { onEditClick(task.id) },
                        onDelete = { viewModel.deleteTask(task) }
                    )
                }
            }
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onToggle: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = task.isDone,
                onCheckedChange = { onToggle() }
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (task.isDone) {
                        TextDecoration.LineThrough
                    } else {
                        TextDecoration.None
                    }
                )

                Text(
                    text = task.subject,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            OutlinedButton(onClick = onEdit) {
                Text("Edit")
            }

            OutlinedButton(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}
