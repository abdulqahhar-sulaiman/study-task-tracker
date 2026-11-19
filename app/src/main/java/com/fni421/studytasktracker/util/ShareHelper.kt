package com.fni421.studytasktracker.util

import android.content.Context
import android.content.Intent
import com.fni421.studytasktracker.data.Task

fun shareTask(context: Context, task: Task) {

    val Sharetxt = """
        Study Task: ${task.title}
        Subject: ${task.subject}
        Status: ${if (task.isDone) "completed" else "not completed"}
    """.trimIndent()

    val intent = Intent(Intent.ACTION_SEND).apply {
        type=  "text/plain"
         putExtra(Intent.EXTRA_SUBJECT, task.title)
         putExtra(Intent.EXTRA_TEXT, Sharetxt)
    }

    val choose = Intent.createChooser(intent, "Share Study Task")
    context.startActivity(choose)
}