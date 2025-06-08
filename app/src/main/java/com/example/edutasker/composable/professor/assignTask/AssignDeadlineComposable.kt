package com.example.edutasker.composable.professor.assignTask

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.edutasker.R
import com.example.edutasker.composable.task.preview.rememberDatePickerDialog
import com.example.edutasker.utils.noRippleClickable
import com.example.edutasker.ui.theme.Cream

@Composable
fun AssignDeadlineComposable(deadline: (String) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current
    var deadlineDate by remember { mutableStateOf("") }
    val datePickerDialog = rememberDatePickerDialog(context) { selectedDate ->
        deadlineDate = selectedDate
        deadline(deadlineDate)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(1.dp, Color.DarkGray, shape = MaterialTheme.shapes.small)
            .noRippleClickable(interactionSource = interactionSource, onClick = {
                datePickerDialog.show()
            }),
        color = Cream,
        shape = MaterialTheme.shapes.small
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (deadlineDate.isNotEmpty()) {
                Text(
                    text = deadlineDate,
                    color = Color.Black,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            } else {
                Text(
                    text = stringResource(R.string.deadline),
                    color = Color.Black,
                    fontSize = 18.sp
                )
            }
        }
    }
}
