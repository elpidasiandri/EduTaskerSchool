package com.example.edutasker.composable.task.preview.professorActionsOnView

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EditableTitleFieldComposable(
    title: String,
    onTitleChange: (String) -> Unit,
    onDoneEditing: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = title,
        onValueChange = onTitleChange,
        modifier = modifier,
        textStyle = TextStyle(fontSize = 20.sp),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        trailingIcon = {
            IconButton(
                modifier = Modifier.padding(top = 8.dp),
                onClick = onDoneEditing
            ) {
                Icon(Icons.Default.Check, contentDescription = "Save title")
            }
        }
    )
}