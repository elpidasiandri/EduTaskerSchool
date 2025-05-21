package com.example.edutasker.composable.task.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.edutasker.model.OpenedTask

@Composable
fun TaskDetailsDialog(
    taskInfo: OpenedTask,
    onDismiss: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f))
            .clickable(onClick = onDismiss)
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.85f)
                .wrapContentHeight()
                .clickable(enabled = false) {},
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(taskInfo.taskInfo.taskTitle, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(Modifier.height(8.dp))
                Text(taskInfo.taskInfo.description, fontSize = 14.sp)

                Divider(modifier = Modifier.padding(vertical = 12.dp))

                Text("Assigned to: ${taskInfo.taskInfo.assignTo}")
                Text("Assigned by: ${taskInfo.taskInfo.assignByProfessor}")
                Text("Subject: ${taskInfo.taskInfo.subjectName}")
                Text("Created: ${taskInfo.taskInfo.creationDate}")
                Text("Deadline: ${taskInfo.taskInfo.deadlineDate}")
            }
        }
    }
}
