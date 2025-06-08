package com.example.edutasker.composable.task.preview.professorActionsOnView

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.edutasker.R
import com.example.edutasker.composable.task.preview.DropdownMenuStatusSelector
import com.example.edutasker.model.TaskStatus
import com.example.edutasker.ui.theme.Purple

@Composable
fun ProfessorTaskDetailsAboutProgressSectionComposable(
    selectedStatus: TaskStatus,
    onStatusSelected: (TaskStatus) -> Unit,
) {
    val isDone = selectedStatus == TaskStatus.DONE

    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .padding(top = 15.dp)
                .padding(end = 5.dp),
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
                modifier = Modifier
                    .padding(start = 4.dp)
                    .padding(top = 15.dp),
                text = if (selectedStatus == TaskStatus.IN_PROGRESS) stringResource(R.string.in_progress_caps) else selectedStatus.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Purple,
            )
        }
    }
}