package com.example.edutasker.composable.notification.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import com.example.edutasker.composable.errorOrSuccessToast.CustomToastComposable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.edutasker.R
import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.NotificationsDetails
import com.example.edutasker.ui.theme.Cream

@Composable
fun FullScreenNotificationUI(
    notifications: List<NotificationsDetails>,
    onDismiss: () -> Unit,
    onNotificationClick: (String) -> Unit,
    errorMessage: String? = null,
    initializeMessage: () -> Unit,
    openTask: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .fillMaxHeight()
            .background(Cream, shape = RoundedCornerShape(16.dp))
            .padding(top = 20.dp, bottom = 20.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.notifications),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (notifications.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.no_notifications),
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(notifications) { notification ->
                        NotificationItemComposable(
                            notification = notification,
                            onClick = {
                                if (CurrentUser.getCurrentUserIfIsStudent() && !notification.readableByStudent) {
                                    onNotificationClick(notification.notificationId)
                                } else if (!CurrentUser.getCurrentUserIfIsStudent() && !notification.readableByProfessor) {
                                    onNotificationClick(notification.notificationId)
                                }
                                openTask(notification.taskDetails.taskId)
                            }
                        )
                    }
                }
            }
        }
        errorMessage?.let { errorMessageNotNull ->
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            ) {
                CustomToastComposable(
                    isError = true,
                    message = errorMessageNotNull,
                    initializeMessage = { initializeMessage() }
                )
            }
        }
    }
}
