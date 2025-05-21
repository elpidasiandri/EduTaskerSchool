package com.example.edutasker.composable.task.overview

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.font.FontWeight

@Composable
fun DropdownMenuStatusSelector(
    currentStatus: String,
    onStatusSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        TextButton(onClick = { expanded = true }) {
            Text(
                text = currentStatus,
                fontWeight = FontWeight.Bold,
                color = when (currentStatus) {
                    "DONE" -> Color.Green
                    else -> Color.Black
                }
            )
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            listOf("TODO", "IN_PROGRESS", "DONE", "CLOSED").forEach { status ->
                DropdownMenuItem(onClick = {
                    onStatusSelected(status)
                    expanded = false
                }) {
                    Text(status)
                }
            }
        }
    }
}
