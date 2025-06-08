package com.example.edutasker.composable.task.preview

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.edutasker.R
import com.example.edutasker.model.TaskStatus

@Composable
fun DropdownMenuStatusSelector(
    currentStatus: String,
    onStatusSelected: (String) -> Unit,
    allowedStatuses: List<String> = listOf(
        TaskStatus.TODO.name, TaskStatus.IN_PROGRESS.name,
        TaskStatus.DONE.name, TaskStatus.CLOSED.name
    )
) {
    var expanded by remember { mutableStateOf(false) }

    TextButton(
        contentPadding = PaddingValues(0.dp),
        onClick = { expanded = true }) {
        Text(
            text = currentStatus,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )
        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }) {
        allowedStatuses.forEach { status ->
            DropdownMenuItem(onClick = {
                onStatusSelected(status)
                expanded = false
            }) {
                Text(
                    if (status == TaskStatus.IN_PROGRESS.name) {
                        stringResource(R.string.in_progress_caps)
                    } else status
                )
            }
        }
    }
}