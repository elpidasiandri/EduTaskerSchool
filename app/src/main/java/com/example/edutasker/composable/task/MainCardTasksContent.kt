package com.example.edutasker.composable.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.edutasker.R
import com.example.edutasker.composable.professor.Task
import com.example.edutasker.model.TaskStatus
import com.example.edutasker.ui.theme.Green
import com.example.edutasker.ui.theme.LightOcean
import com.example.edutasker.ui.theme.Yellow

@Composable
fun MainCardTasksContent(modifier: Modifier = Modifier) {
    val sampleTasks = listOf(
        Task(1, "Essay on Ethics", "Student A", "12/05/2025", TaskStatus.TODO),
        Task(2, "Research Report", "Student B", "15/05/2025", TaskStatus.IN_PROGRESS),
        Task(3, "Final Project", "Student C", "20/05/2025", TaskStatus.DONE),
        Task(4, "Midterm Review", "Student D", "17/05/2025", TaskStatus.TODO),
        Task(5, "Presentation", "Student E", "18/05/2025", TaskStatus.IN_PROGRESS),
        Task(6, "Essay Review", "Student F", "19/05/2025", TaskStatus.DONE)
    )

    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val spacing = 8.dp * 2
        val columnWidth = (screenWidth - spacing) / 3


        TaskColumn(
            title = stringResource(R.string.to_do),
            tasks = sampleTasks.filter { it.status == TaskStatus.TODO },
            backgroundColor = LightOcean,
            modifier = Modifier
                .width(columnWidth)
                .fillMaxHeight()
        )
        TaskColumn(
            title = stringResource(R.string.in_progress),
            tasks = sampleTasks.filter { it.status == TaskStatus.IN_PROGRESS },
            backgroundColor = Yellow,
            modifier = Modifier
                .width(columnWidth)
                .fillMaxHeight()
        )
        TaskColumn(
            title = stringResource(R.string.done),
            tasks = sampleTasks.filter { it.status == TaskStatus.DONE },
            backgroundColor = Green,
            modifier = Modifier
                .width(columnWidth)
                .fillMaxHeight()
        )
    }
}
