package com.example.edutasker.composable.task.preview.professorActionsOnView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.edutasker.R
import com.example.edutasker.composable.task.preview.DropdownMenuStatusSelector
import com.example.edutasker.model.TaskStatus

@Composable
fun ProfessorTaskDetailsAboutProgressSectionComposable(
    selectedStatus: TaskStatus,
    onStatusSelected: (TaskStatus) -> Unit,
) {
    val isDone = selectedStatus == TaskStatus.DONE

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.status),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )

        if (isDone) {
            DropdownMenuStatusSelector(
                currentStatus = selectedStatus.name,
                onStatusSelected = { statusStr ->
                    if (statusStr == TaskStatus.DONE.name || statusStr == TaskStatus.CLOSED.name) {
                        onStatusSelected(TaskStatus.valueOf(statusStr))
                    }
                },
                allowedStatuses = listOf(TaskStatus.DONE.name, TaskStatus.CLOSED.name)
            )
        } else {
            Text(
                text = if (selectedStatus == TaskStatus.IN_PROGRESS) stringResource(R.string.in_progress_caps) else selectedStatus.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
            )
        }
    }
}