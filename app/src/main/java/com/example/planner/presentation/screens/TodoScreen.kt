package com.example.planner.presentation.screens

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.planner.R
import com.example.planner.presentation.composables.TodoCard
import com.example.planner.presentation.viewdata.StatusViewData
import com.example.planner.presentation.viewdata.TodoViewData
import com.example.planner.presentation.viewmodels.TodoScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(navController: NavController) {
    val viewModel = hiltViewModel<TodoScreenViewModel>()
    val mockTodos by viewModel.todos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Planner App") },
                actions = {
                    IconButton(onClick = {
                        viewModel.logout()
                        navController.navigate(Routes.Login.route)
                    }) {
                        Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "Logout")

                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "add todo",
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { contentPadding ->
        LazyColumn(
            contentPadding = contentPadding,
            modifier = Modifier.fillMaxHeight(),
        ) {
            items(mockTodos.size) { index ->
                TodoCard(todoViewData = mockTodos[index])
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun TodoScreenPreview() {
    TodoScreen(navController = rememberNavController())
}
