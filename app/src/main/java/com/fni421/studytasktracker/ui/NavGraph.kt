package com.fni421.studytasktracker.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fni421.studytasktracker.viewmodel.TaskViewModel

private const val ROUTE_LIST = "list"
private const val ROUTE_EDIT = "edit/{taskId}"

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: TaskViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_LIST,
        modifier = modifier
    ) {
        composable(ROUTE_LIST) {
            TaskListScreen(
                viewModel = viewModel,
                onAddClick = { navController.navigate("edit/-1") },
                onEditClick = { id -> navController.navigate("edit/$id") }
            )
        }

        composable(
            route = ROUTE_EDIT,
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: -1
            TaskEditScreen(
                viewModel = viewModel,
                taskId = taskId,
                onDone = { navController.popBackStack() }
            )
        }
    }
}
