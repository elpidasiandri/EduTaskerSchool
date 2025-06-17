package com.example.edutasker.composable.task.taskprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.edutasker.R
import com.example.edutasker.model.TasksWithStudentImageModel
import com.example.edutasker.ui.theme.Cream

@Composable
fun TaskCardComposable(
    taskWithStudentImage: TasksWithStudentImageModel,
    studentImage: String,
    onTaskClick: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                onTaskClick(taskWithStudentImage.task.taskId)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(6.dp)
    ) {
        Column(modifier = Modifier.background(Cream)) {
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .padding(top = 4.dp),
                text = stringResource(R.string.title) + ":",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = taskWithStudentImage.task.taskTitle,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                fontSize = 11.sp,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = stringResource(R.string.description) + ":",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = taskWithStudentImage.task.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 3,
                fontSize = 11.sp,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = stringResource(R.string.subject) + ":",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = taskWithStudentImage.task.subjectName,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 3,
                fontSize = 11.sp,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                AsyncImage(
                    model = studentImage.ifEmpty { R.raw.studentavatar },
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .padding(start = 3.dp)
                        .padding(bottom = 4.dp)
                        .border(1.dp, Color.Gray, CircleShape),
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .padding(bottom = 4.dp),
                    text = taskWithStudentImage.task.deadlineDate,
                    fontSize = 10.sp
                )
            }
        }
    }
}