package com.example.edutasker.composable.professor.assignTask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.edutasker.R
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.screens.professor.viewModel.stateAndEvents.ProfessorEvents
import com.example.edutasker.ui.theme.LightBlue

@Composable
fun AddTaskDialogComposable(
    onEvent: (ProfessorEvents) -> Unit,
    subjects: List<String>,
    searchedStudents: List<StudentPreviewAsListModel>,
) {
    var fileUriInfo by remember { mutableStateOf("") }
    var deadlineDateOfTask by remember { mutableStateOf("") }
    var descriptionOfTask by remember { mutableStateOf("") }
    var titleOfTask by remember { mutableStateOf("") }
    var selectedSubjectOfTask by remember { mutableStateOf("") }
    var assignToSearch by remember { mutableStateOf("") }
    var selectedStudentsOfTask by remember { mutableStateOf(listOf<StudentPreviewAsListModel>()) }
    val filteredStudents = searchedStudents.filter { student ->
        student.username.contains(assignToSearch, ignoreCase = true) &&
                student !in selectedStudentsOfTask
    }
    val isFormValid by remember(
        selectedSubjectOfTask,
        descriptionOfTask,
        selectedStudentsOfTask,
        deadlineDateOfTask,
        titleOfTask
    ) {
        derivedStateOf {
            selectedSubjectOfTask.isNotBlank() &&
                    descriptionOfTask.isNotBlank() &&
                    selectedStudentsOfTask.isNotEmpty() &&
                    titleOfTask.isNotEmpty() &&
                    deadlineDateOfTask.isNotBlank()
        }
    }

    AlertDialog(
        onDismissRequest = {
            onEvent(ProfessorEvents.DismissAddTaskScreen)
        },
        backgroundColor = LightBlue,
        title = {
            Text(
                stringResource(R.string.new_task), color = Color.White,
                fontSize = 22.sp
            )
        },
        text = {
            Column() {
                WriteTaskTitleComposable({ title ->
                    titleOfTask = title
                })
                SubjectDropdownComposable(
                    selectedSubject = selectedSubjectOfTask,
                    onSubjectSelected = { selectedSubjectOfTask = it },
                    subjects = subjects
                )

                Spacer(modifier = Modifier.height(8.dp))

                WriteDescriptionOfTaskAndAttachFileComposable(
                    { des -> descriptionOfTask = des },
                    { info -> fileUriInfo = info })

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, top = 8.dp)
                ) {
                    items(selectedStudentsOfTask) { student ->
                        Row(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .background(Color.DarkGray, shape = RoundedCornerShape(16.dp))
                                .clickable {
                                    selectedStudentsOfTask = selectedStudentsOfTask - student
                                }
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = student.image,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = student.username,
                                color = Color.White,
                                fontSize = 14.sp,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    }
                }
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = assignToSearch,
                    onValueChange = {
                        assignToSearch = it
                        onEvent(ProfessorEvents.SearchProfessorStudents(selectedSubjectOfTask))
                    },
                    label = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (selectedStudentsOfTask.isEmpty()) {
                                    stringResource(R.string.assign_to)
                                } else {
                                    "${stringResource(R.string.assigned_to)}: ${
                                        selectedStudentsOfTask.joinToString { it.username }
                                    }"
                                },
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                    },
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp
                    )
                )

                if (assignToSearch.isNotEmpty()) {
                    Column(modifier = Modifier.padding(top = 12.dp)) {
                        filteredStudents.forEach { student ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedStudentsOfTask = selectedStudentsOfTask + student
                                        assignToSearch = ""
                                    }
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = student.image.ifEmpty { R.raw.studentavatar },
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = student.username, color = Color.White)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                AssignDeadlineComposable(
                    deadline = { date ->
                        deadlineDateOfTask = date
                    }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onEvent(
                    ProfessorEvents.AddTask(
                        title = titleOfTask,
                        subjectName = selectedSubjectOfTask,
                        description = descriptionOfTask,
                        deadline = deadlineDateOfTask,
                        selectedUsers = selectedStudentsOfTask.map {
                            it.studentId
                        }
                    ))
            }, enabled = isFormValid) {
                Text(
                    stringResource(R.string.save),
                    color = if (isFormValid) Color.White else Color.DarkGray,
                    fontSize = 14.sp
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onEvent(ProfessorEvents.DismissAddTaskScreen)
            }) {
                Text(stringResource(R.string.cancel), color = Color.White, fontSize = 14.sp)
            }
        }
    )
}
