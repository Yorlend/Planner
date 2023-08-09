package com.example.planner.presentation.screens

import android.graphics.Color
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDismissState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.planner.R
import com.example.planner.presentation.composables.AddTodo
import com.example.planner.presentation.composables.DismissBackground
import com.example.planner.presentation.composables.TodoCard
import com.example.planner.presentation.viewdata.ShowViewData
import com.example.planner.presentation.viewmodels.TodoScreenViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(navController: NavController) {
    val viewModel = hiltViewModel<TodoScreenViewModel>()
    val todos by viewModel.todos.collectAsState()
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState()

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
            FloatingActionButton(onClick = { openBottomSheet = !openBottomSheet }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "add todo",
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { contentPadding ->
        LazyColumn(
            contentPadding = contentPadding,
            modifier = Modifier.fillMaxHeight(),
        ) {
            items(todos.size) { index ->
                key(todos[index]) {
                    val context = LocalContext.current
                    var show by remember { mutableStateOf(true) }
                    var transition by remember { mutableStateOf(ShowViewData.DEFAULT) }
                    val dismissState = rememberDismissState(
                        confirmValueChange = {
                            when (it) {
                                DismissValue.DismissedToStart -> {
                                    show = false
                                    transition = ShowViewData.TOGGLE
                                    true
                                }

                                DismissValue.DismissedToEnd -> {
                                    show = false
                                    transition = ShowViewData.DELETE
                                    true
                                }

                                else -> false
                            }
                        }
                    )
                    AnimatedVisibility(show, exit = fadeOut(spring())) {
                        SwipeToDismiss(
                            state = dismissState,
                            directions = setOf(
                                DismissDirection.EndToStart,
                                DismissDirection.StartToEnd
                            ),
                            background = {
                                DismissBackground(dismissState = dismissState)
                            },
                            dismissContent = {
                                TodoCard(
                                    todoViewData = todos[index]
                                )
                            }
                        )
                    }

                    LaunchedEffect(show) {
                        if (!show) {
                            delay(800)
                            when (transition) {
                                ShowViewData.DELETE -> {
                                    viewModel.remove(todos[index])
                                    Toast.makeText(context, "Item Removed", Toast.LENGTH_SHORT)
                                        .show()
                                }

                                ShowViewData.TOGGLE -> {
                                    viewModel.toggle(todos[index])
                                    Toast.makeText(context, "Item Checked", Toast.LENGTH_SHORT)
                                        .show()
                                }

                                else -> null
                            }
                        }
                    }
                }
            }
        }

        if (openBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { openBottomSheet = false },
                sheetState = bottomSheetState,
            ) {
                AddTodo { todo -> viewModel.add(todo) }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun TodoScreenPreview() {
    TodoScreen(navController = rememberNavController())
}
