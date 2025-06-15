package com.example.edutasker.composable.professor

import android.annotation.SuppressLint
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
import com.example.edutasker.composable.notification.NotificationDialogComposable
import com.example.edutasker.composable.notification.NotificationBadgeComposable
import com.example.edutasker.composable.task.taskprofile.MainCardTasksContentComposable
import com.example.edutasker.ui.theme.Blue
import kotlinx.coroutines.launch
import androidx.compose.material.BottomAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.edutasker.composable.professor.arrowWithStudents.StudentAvatarRowComposable
import com.example.edutasker.composable.professor.assignTask.AddTaskDialogComposable
import com.example.edutasker.composable.professor.menu.MenuProfessorComposable
import com.example.edutasker.composable.professor.searchBar.ResultsOfSearchedStudentsComposable
import com.example.edutasker.composable.professor.searchBar.StudentSearchBarComposable
import com.example.edutasker.composable.task.preview.TaskDetailsDialog
import com.example.edutasker.screens.professor.viewModel.stateAndEvents.ProfessorEvents
import com.example.edutasker.screens.professor.viewModel.stateAndEvents.ProfessorState
import com.example.edutasker.ui.theme.Cream

@SuppressLint("ResourceType")
@Composable
fun ProfessorScreenComposable(
    onEvent: (ProfessorEvents) -> Unit,
    state: ProfessorState,
) {
    var loadSuggestedSubjectsToAssignTask by remember(
        state.professorSubjects,
        state.selectedStudentForAddingAssignment
    ) {
        mutableStateOf(
            true
        )
    }

    if (state.isTaskOpened) {
        TaskDetailsDialog(
            taskInfo = state.openedTask, onDismiss = {
                onEvent(ProfessorEvents.CloseTaskDialog)
            },
            onSaveStatusChange = { taskInfo ->
                onEvent(ProfessorEvents.UpdateTask(taskInfo))
            }
        )
    }

    if (state.isNotificationDialogVisible) {
        NotificationDialogComposable(
            notifications = state.notifications,
            onDismiss = {
                onEvent(ProfessorEvents.CloseNotificationDialog)
            },
            onNotificationClick = { notificationId ->
                onEvent(ProfessorEvents.MarkNotificationAsRead(notificationId))
            }
        )
    }

    if (state.isAddDialogVisible) {
        if (loadSuggestedSubjectsToAssignTask) {
            onEvent(ProfessorEvents.GetSubjectsOfProfessor)
            loadSuggestedSubjectsToAssignTask = false
        }
        AddTaskDialogComposable(
            onEvent = onEvent,
            subjects = state.professorSubjects,
            searchedStudents = state.searchedStudentsForAssignment
        )
    }
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val exitBitmap = remember {
        BitmapFactory.decodeStream(context.resources.openRawResource(R.raw.exit_icon))
    }
    val notificationBitmap = remember {
        BitmapFactory.decodeStream(context.resources.openRawResource(R.raw.notification))
    }
    val addBitmap = remember {
        BitmapFactory.decodeStream(context.resources.openRawResource(R.raw.add))
    }
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            MenuProfessorComposable(onEvent, scaffoldState)
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
                            text = stringResource(R.string.edu_tasker_professor),
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
                IconButton(onClick = {
                    onEvent(ProfessorEvents.WillingToAddTask)
                }) {
                    Image(
                        bitmap = addBitmap.asImageBitmap(),
                        contentDescription = "Add",
                        modifier = Modifier.size(28.dp)
                    )
                }
                Box {
                    IconButton(onClick = {
                        onEvent(ProfessorEvents.OpenNotificationDialog)
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
                    onEvent(ProfessorEvents.Logout)
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
                StudentSearchBarComposable(
                    keyword = state.keyword,
                    selectedStudentIdFromSearch = state.selectedStudentFromSearch.studentId,
                    onQueryChange = { keyword ->
                        onEvent(ProfessorEvents.SearchStudents(keyword))
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                ResultsOfSearchedStudentsComposable(
                    state.searchedStudents,
                    keyword = state.keyword,
                ) { student ->
                    onEvent(ProfessorEvents.SelectStudentToSeeBacklog(student))
                }
                StudentAvatarRowComposable(
                    modifier = Modifier
                        .padding(8.dp),
                    students = state.studentsToAppearOnCentralRow,
                    selectedStudentIdFromSearch = state.selectedStudentFromSearch.studentId
                ) { student ->
                    onEvent(ProfessorEvents.SelectStudentToSeeBacklog(student))
                }
                Spacer(modifier = Modifier.height(8.dp))
                MainCardTasksContentComposable(
                    modifier = Modifier
                        .background(Cream)
                        .padding(padding),
                    selectedStudentImage = state.selectedStudentFromSearch.image,
                    allTasksByEveryoneWithImage = state.allTasksByProfessorStudent.ifEmpty { state.allTasksByEveryone },
                    onTaskClick = { taskId -> onEvent(ProfessorEvents.OpenTaskDialog(taskId)) },
                    unreadTaskIds = state.unreadTaskIds
                )
            }
        }
    )
}