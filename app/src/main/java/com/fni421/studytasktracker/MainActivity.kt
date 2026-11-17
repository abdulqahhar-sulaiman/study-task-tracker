package com.fni421.studytasktracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.fni421.studytasktracker.data.SettingsStore
import com.fni421.studytasktracker.ui.AppNavGraph
import com.fni421.studytasktracker.ui.theme.StudyTaskTrackerTheme
import com.fni421.studytasktracker.viewmodel.TaskViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val settings = SettingsStore(applicationContext)

        setContent {
            val darkMode by settings.darkMode.collectAsState(initial = isSystemInDarkTheme())
            val scope = rememberCoroutineScope()
            val navController = rememberNavController()
            val viewModel: TaskViewModel = viewModel()

            StudyTaskTrackerTheme(darkTheme = darkMode) {
                Scaffold(
                    topBar = {
                        AppTopBar(
                            darkMode = darkMode,
                            onToggleTheme = { scope.launch { settings.setDarkMode(!darkMode) } }
                        )
                    }
                ) { innerPadding ->
                    AppNavGraph(
                        navController = navController,
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(darkMode: Boolean, onToggleTheme: () -> Unit) {
    TopAppBar(
        title = { Text("Study Task Tracker") },
        actions = {
            Text("Dark mode")
            Switch(
                checked = darkMode,
                onCheckedChange = { onToggleTheme() },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    )
}
