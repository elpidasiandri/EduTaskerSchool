package com.example.edutasker.composable.notification.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.edutasker.R
import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.NotificationsDetails
import com.example.edutasker.ui.theme.PurpleGrey40
import com.example.edutasker.ui.theme.Red

@Composable
fun NotificationItemComposable(
    notification: NotificationsDetails,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if ((CurrentUser.getCurrentUserIfIsStudent() && !notification.readableByStudent) || (!CurrentUser.getCurrentUserIfIsStudent() && !notification.readableByProfessor)) PurpleGrey40 else Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .background(if ((CurrentUser.getCurrentUserIfIsStudent() && !notification.readableByStudent) || (!CurrentUser.getCurrentUserIfIsStudent() && !notification.readableByProfessor)) PurpleGrey40 else Color.White)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = if (CurrentUser.getCurrentUserIfIsStudent()) notification.professorDetails.image.ifEmpty { R.raw.professoravatar } else notification.studentBasic.image.ifEmpty { R.raw.studentavatar },
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = notification.taskDetails.taskTitle,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "by ${notification.professorDetails.username}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = notification.taskDetails.description,
                fontSize = 14.sp,
                color = Color.Black,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Subject: ${notification.taskDetails.subjectName}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Due: ${notification.taskDetails.deadlineDate}",
                    fontSize = 12.sp,
                    color = Red,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Created: ${notification.taskDetails.creationDate}",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}