package com.example.edutasker.composable.task.taskprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.edutasker.model.TasksWithStudentImageModel

@Composable
fun TaskColumnComposable(
    title: String,
    tasks: List<TasksWithStudentImageModel>,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    selectedStudentImage: String,
    onTaskClick: (String) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxHeight()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(backgroundColor)
                .padding(4.dp)
        ) {
            Text(
                title,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            Divider(modifier = Modifier.padding(vertical = 4.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(tasks) { task ->
                    TaskCardComposable(
                        taskWithStudentImage = task,
                        studentImage = selectedStudentImage,
                        onTaskClick = { taskId -> onTaskClick(taskId) }
                    )
                }
            }
        }
    }
}