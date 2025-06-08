package com.example.edutasker.composable.task.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.edutasker.R
import com.example.edutasker.composable.task.preview.professorActionsOnView.EditableDescriptionFieldComposable
import com.example.edutasker.composable.task.preview.professorActionsOnView.EditableTitleFieldComposable
import com.example.edutasker.composable.task.preview.professorActionsOnView.ProfessorTaskDetailsAboutProgressSectionComposable
import com.example.edutasker.composable.task.preview.studentActionsOnView.ReadOnlyTitleRowComposable
import com.example.edutasker.composable.task.preview.studentActionsOnView.StudentTaskDetailsAboutProgressSectionComposable
import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.OpenedTaskModel
import com.example.edutasker.model.ProfessorBasicModel
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.model.TaskModel
import com.example.edutasker.model.TaskStatus
import com.example.edutasker.model.UpdateTaskByProfessorModel
import com.example.edutasker.ui.theme.Cream
import com.example.edutasker.ui.theme.Red

@Composable
fun TaskDetailsDialog(
    taskInfo: OpenedTaskModel,
    onDismiss: () -> Unit,
    onSaveStatusChange: (UpdateTaskByProfessorModel) -> Unit,
) {
    val isStudent = CurrentUser.getCurrentUserIfIsStudent()
    var selectedStatus by remember { mutableStateOf(TaskStatus.valueOf(taskInfo.taskInfo.progress.name)) }
    var editableDescription by remember { mutableStateOf(taskInfo.taskInfo.description) }

    var isEditingTitle by remember { mutableStateOf(false) }
    var isEditingDeadline by remember { mutableStateOf(false) }
    var isEditingDescription by remember { mutableStateOf(false) }

    var editableTitle by remember { mutableStateOf(taskInfo.taskInfo.taskTitle) }
    var editableDeadline by remember { mutableStateOf(taskInfo.taskInfo.deadlineDate) }
    val hasChanges =
        selectedStatus != taskInfo.taskInfo.progress || taskInfo.taskInfo.taskTitle != editableTitle ||
                taskInfo.taskInfo.description != editableDescription ||
                taskInfo.taskInfo.deadlineDate != editableDeadline
    val context = LocalContext.current
    val datePickerDialog = rememberDatePickerDialog(context) { selectedDate ->
        editableDeadline = selectedDate
        isEditingDeadline = false
    }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.9f)
                .background(Cream, shape = RoundedCornerShape(16.dp))
                .padding(20.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (isStudent) {
                            StudentTaskDetailsAboutProgressSectionComposable(
                                selectedStatus = selectedStatus,
                                onStatusSelected = { selectedStatus = it },
                                onDismiss = onDismiss
                            )
                        } else {
                            ProfessorTaskDetailsAboutProgressSectionComposable(
                                selectedStatus = selectedStatus,
                                onStatusSelected = { selectedStatus = it }
                            )
                        }
                    }

                    if (isEditingTitle && !isStudent) {
                        EditableTitleFieldComposable(
                            title = editableTitle,
                            onTitleChange = { editableTitle = it },
                            onDoneEditing = { isEditingTitle = false },
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        ReadOnlyTitleRowComposable(
                            title = editableTitle,
                            isStudent = isStudent,
                            onEditClick = { isEditingTitle = true }
                        )
                    }

                    if (isEditingDescription && !isStudent) {
                        EditableDescriptionFieldComposable(
                            value = editableDescription,
                            onValueChange = { editableDescription = it },
                            onSaveClick = { isEditingDescription = false },

                            )
                    } else {
                        Row(
                            modifier = Modifier
                                .clickable(enabled = !isStudent) { isEditingDescription = true }
                                .padding(bottom = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = editableDescription,
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )
                            if (!isStudent) {
                                AsyncImage(
                                    model = R.raw.pencil,
                                    contentDescription = "Edit description",
                                    modifier = Modifier
                                        .padding(start = 4.dp)
                                        .size(18.dp)
                                )
                            }
                        }
                    }
                    Divider(modifier = Modifier.padding(vertical = 12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.deadline) + ": ",
                            fontSize = 16.sp,
                            fontWeight = Bold
                        )
                        Text(
                            color = Red,
                            text = editableDeadline
                        )
                        if (!isStudent) {
                            IconButton(
                                onClick = {
                                    isEditingDeadline = true
                                    datePickerDialog.show()
                                },
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .size(24.dp)
                            ) {
                                AsyncImage(
                                    model = R.raw.pencil,
                                    contentDescription = "Edit deadline",
                                    modifier = Modifier
                                        .size(14.dp)
                                )
                            }
                        }
                    }
                    ProfileRow(
                        stringResource(R.string.assigned_by),
                        taskInfo.professorBasic.username,
                        taskInfo.professorBasic.image,
                        isStudent = false
                    )
                    ProfileRow(
                        stringResource(R.string.assigned_to),
                        taskInfo.studentBasic.username,
                        taskInfo.studentBasic.image,
                        isStudent = true
                    )
                    TaskDetailRow(stringResource(R.string.subject), taskInfo.taskInfo.subjectName)
                    TaskDetailRow(stringResource(R.string.created), taskInfo.taskInfo.creationDate)

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text(text = stringResource(R.string.cancel))
                    }
                    Button(
                        shape = RoundedCornerShape(8.dp),
                        enabled = hasChanges,
                        onClick = {
                            onSaveStatusChange(
                                UpdateTaskByProfessorModel(
                                    taskId = taskInfo.taskInfo.taskId,
                                    taskDescription = editableDescription,
                                    taskTitle = editableTitle,
                                    taskDeadline = editableDeadline,
                                    progress = selectedStatus.name
                                )
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (hasChanges) MaterialTheme.colorScheme.primary else Color.Gray,
                            contentColor = Color.White,
                        )
                    ) {
                        Text(stringResource(R.string.save))
                    }
                }
            }
        }
    }
}

@Preview()
@Composable
fun TaskDetailsDialogPreview() {
    TaskDetailsDialog(
        taskInfo = OpenedTaskModel(
            taskInfo = TaskModel(
                taskId = "1",
                taskTitle = "Say no to kids",
                subjectName = "Arthritika",
                description = "BaseActivity To reduce boilerplate code across activities, a reusable abstract class BaseActivity has been created.The setup, there, allows each activity to simply pass its specific ViewBinding, minimizing code repetition and avoiding the need to manually nullify bindings in every onDestroy.",
                assignTo = "1",
                assignByProfessor = "1234",
                deadlineDate = "13/08/2025",
                creationDate = "12/05/2025",
                progress = TaskStatus.DONE
            ),
            studentBasic = StudentPreviewAsListModel(
                studentId = "1",
                username = "elpida",
                image = "https://cdn-front.freepik.com/meta-tags-social/og-fb-logo-en.png"
            ),
            professorBasic = ProfessorBasicModel(
                id = "1234",
                username = "thanasis",
                image = "https://img.freepik.com/free-vector/hand-drawn-diversity-concept-background_23-2148176053.jpg"
            )
        ), onDismiss = {}, onSaveStatusChange = {})
}