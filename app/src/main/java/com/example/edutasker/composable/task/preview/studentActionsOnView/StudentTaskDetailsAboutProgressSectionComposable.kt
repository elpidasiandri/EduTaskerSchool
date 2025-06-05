package com.example.edutasker.composable.task.preview.studentActionsOnView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.edutasker.R
import com.example.edutasker.composable.task.preview.DropdownMenuStatusSelector
import com.example.edutasker.model.TaskStatus

@Composable
fun StudentTaskDetailsAboutProgressSectionComposable(
    selectedStatus: TaskStatus,
    onStatusSelected: (TaskStatus) -> Unit,
    onDismiss: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DropdownMenuStatusSelector(
            currentStatus = if (selectedStatus == TaskStatus.IN_PROGRESS) stringResource(R.string.in_progress_caps) else selectedStatus.name,
            onStatusSelected = { statusStr ->
                onStatusSelected(TaskStatus.valueOf(statusStr))
            },
            allowedStatuses = listOf(
                TaskStatus.DONE.name,
                TaskStatus.IN_PROGRESS.name,
                TaskStatus.TODO.name
            )
        )
        IconButton(
            modifier = Modifier.padding(top = 8.dp),
            onClick = onDismiss
        ) {
            Icon(Icons.Default.Close, contentDescription = "Close")
        }
    }
}
