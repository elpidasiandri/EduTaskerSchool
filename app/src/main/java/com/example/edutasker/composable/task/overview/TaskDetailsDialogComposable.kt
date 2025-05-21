package com.example.edutasker.composable.task.overview

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.edutasker.R
import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.OpenedTaskModel
import com.example.edutasker.model.TaskStatus

@Composable
fun TaskDetailsDialog(
    taskInfo: OpenedTaskModel,
    onDismiss: () -> Unit,
    onStatusChange: (String) -> Unit,
    onSaveStatusChange: () -> Unit,
) {
    var selectedStatus by remember { mutableStateOf(TaskStatus.valueOf(taskInfo.taskInfo.progress.name)) }
    val hasChanges = selectedStatus != taskInfo.taskInfo.progress

    Dialog(onDismissRequest = onDismiss) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.9f)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
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
                        if (CurrentUser.getCurrentUserIfIsStudent()) {
                            DropdownMenuStatusSelector(
                                currentStatus = selectedStatus.name,
                                onStatusSelected = { statusStr ->
                                    selectedStatus = TaskStatus.valueOf(statusStr)
                                }
                            )

                            IconButton(onClick = onDismiss) {
                                Icon(Icons.Default.Close, contentDescription = "Close")
                            }
                        } else {
                            Text(
                                text = stringResource(R.string.status) + selectedStatus.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }

                    Text(
                        text = taskInfo.taskInfo.taskTitle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        modifier = Modifier.padding(vertical = 8.dp).padding(top = 8.dp)
                    )

                    Text(
                        text = taskInfo.taskInfo.description,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Divider()

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            ProfileRow(
                                stringResource(R.string.assigned_by),
                                taskInfo.professorBasic.username,
                                taskInfo.professorBasic.image
                            )
                            ProfileRow(
                                stringResource(R.string.assigned_to),
                                taskInfo.studentBasic.username,
                                taskInfo.studentBasic.image
                            )
                            TaskDetailRow(
                                stringResource(R.string.subject),
                                taskInfo.taskInfo.subjectName
                            )
                            TaskDetailRow(
                                stringResource(R.string.deadline),
                                taskInfo.taskInfo.deadlineDate
                            )
                            TaskDetailRow(
                                stringResource(R.string.created),
                                taskInfo.taskInfo.creationDate
                            )
                        }

                    }
                }

                if (hasChanges && CurrentUser.getCurrentUserIfIsStudent()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            onStatusChange(selectedStatus.toString())
                            onSaveStatusChange()
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(stringResource(R.string.save))
                    }
                }
            }
        }
    }
}
