package com.example.edutasker.composable.professor.assignTask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.edutasker.R

@Composable
fun WriteTaskTitleComposable(isTitleEmpty: (String) -> Unit) {
    var title by remember { mutableStateOf("") }
    Column {
        OutlinedTextField(
            value = title,
            onValueChange = {
                isTitleEmpty(it)
                title = it
            },
            label = {
                Text(
                    stringResource(R.string.title),
                    color = Color.Black,
                    fontSize = 18.sp
                )
            },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}