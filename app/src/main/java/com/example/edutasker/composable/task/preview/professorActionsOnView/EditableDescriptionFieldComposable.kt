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
fun EditableDescriptionFieldComposable(
    value: String,
    onValueChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = TextStyle(fontSize = 16.sp),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        trailingIcon = {
            IconButton(
                modifier = Modifier.padding(top = 8.dp),
                onClick = onSaveClick
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save description"
                )
            }
        }
    )
}