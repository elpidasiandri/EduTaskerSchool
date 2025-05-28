package com.example.edutasker.composable.task.overview.studentActionsOnView

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ReadOnlyTitleRowComposable(
    title: String,
    isStudent: Boolean,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.weight(1f)
        )
        if (!isStudent) {
            IconButton(
                modifier = Modifier.padding(top = 8.dp),
                onClick = onEditClick
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit title")
            }
        }
    }
}