package com.example.edutasker.composable.student

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.edutasker.R
import com.example.edutasker.composable.notification.NotificationBadgeComposable
import com.example.edutasker.composable.notification.NotificationDialogComposable
import com.example.edutasker.composable.student.menu.MenuStudentComposable
import com.example.edutasker.composable.task.preview.TaskDetailsDialog
import com.example.edutasker.composable.task.taskprofile.MainCardTasksContentComposable
import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.TasksWithStudentImageModel
import com.example.edutasker.screens.student.viewModel.stateAndEvents.StudentEvents
import com.example.edutasker.screens.student.viewModel.stateAndEvents.StudentState
import com.example.edutasker.ui.theme.Blue
import com.example.edutasker.ui.theme.LightGray
import kotlinx.coroutines.launch

@Composable
fun StudentScreenComposable(
    onEvent: (StudentEvents) -> Unit,
    state: StudentState
) {

    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val notificationBitmap = remember {
        BitmapFactory.decodeStream(context.resources.openRawResource(R.raw.notification))
    }
    val exitBitmap = remember {
        BitmapFactory.decodeStream(context.resources.openRawResource(R.raw.exit_icon))
    }
    val tasksOfUser = remember(state.allTasks) { state.allTasks }

    if (state.isTaskOpened) {
        TaskDetailsDialog(
            taskInfo = state.openedTask, onDismiss = {
                onEvent(StudentEvents.CloseTaskDialog)
            },
            onSaveStatusChange = { taskInfo ->
                onEvent(StudentEvents.UpdateTask(taskInfo))
            }
        )
    }

    if (state.isNotificationDialogVisible) {
        NotificationDialogComposable(
            notifications = state.notifications,
            onDismiss = {
                onEvent(StudentEvents.CloseNotificationDialog)
            },
            onNotificationClick = { notificationId ->
                onEvent(StudentEvents.MarkNotificationAsRead(notificationId))
            }
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            MenuStudentComposable(onEvent, scaffoldState)
        },
        topBar = {
            TopAppBar(
                backgroundColor = Blue,
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = stringResource(R.string.edu_tasker_student),
                            color = Color.White
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                    }
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = Blue,
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Box {
                    IconButton(onClick = {
                        onEvent(StudentEvents.OpenNotificationDialog)
                    }) {
                        Image(
                            bitmap = notificationBitmap.asImageBitmap(),
                            contentDescription = "Notifications",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    NotificationBadgeComposable(
                        count = state.unreadNotificationsCount,
                        modifier = Modifier.padding(start = 20.dp, bottom = 20.dp)
                    )
                }
                IconButton(onClick = {
                    onEvent(StudentEvents.Logout)
                }) {
                    Image(
                        bitmap = exitBitmap.asImageBitmap(),
                        contentDescription = "Exit",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        },
        content = { padding ->
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(8.dp))
                MainCardTasksContentComposable(
                    Modifier
                        .background(LightGray)
                        .padding(padding),
                    CurrentUser.getCurrentUserImage(),
                    allTasksByEveryoneWithImage = tasksOfUser.map {
                        TasksWithStudentImageModel(
                            task = it,
                            studentImage = CurrentUser.getCurrentUserImage()
                        )
                    },
                    onTaskClick = { taskId -> onEvent(StudentEvents.OpenTaskDialog(taskId)) }
                )
            }
        }
    )
}