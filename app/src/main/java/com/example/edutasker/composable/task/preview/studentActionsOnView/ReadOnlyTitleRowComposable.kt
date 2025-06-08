package com.example.edutasker.composable.task.preview.studentActionsOnView

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.example.edutasker.R

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
        )
        if (!isStudent) {
            IconButton(
                onClick = onEditClick
            ) {
                AsyncImage(
                    model = R.raw.pencil,
                    contentDescription = "Edit title",
                    modifier = Modifier
                        .size(18.dp)
                )
            }
        }
    }
}