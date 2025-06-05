package com.example.edutasker.composable.task.preview

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.util.Calendar

@Composable
fun rememberDatePickerDialog(
    context: Context,
    calendar: Calendar = Calendar.getInstance(),
    onDateSelected: (String) -> Unit
): DatePickerDialog {
    return remember {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                onDateSelected(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.minDate = System.currentTimeMillis()
        }
    }
}
