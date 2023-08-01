package com.example.planner.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planner.presentation.viewdata.TodoViewData

@Composable
fun TodoCard(todoViewData: TodoViewData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(todoViewData.completed.color, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = todoViewData.todo,
                maxLines = 3,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun TodoCardPreview() {
    TodoCard(
        todoViewData = TodoViewData(
            todo = "This is a test todo entry",
            user = 1,
        )
    )
}
