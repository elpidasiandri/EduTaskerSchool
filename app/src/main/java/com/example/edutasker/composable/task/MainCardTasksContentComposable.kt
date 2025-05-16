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
import com.example.edutasker.model.TaskStatus
import com.example.edutasker.model.TasksWithStudentImageModel
import com.example.edutasker.ui.theme.Green
import com.example.edutasker.ui.theme.LightOcean
import com.example.edutasker.ui.theme.Yellow

@Composable
fun MainCardTasksContentComposable(
    modifier: Modifier = Modifier,
    selectedStudentImage: String,
    allTasksByEveryoneWithImage: List<TasksWithStudentImageModel>,
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val spacing = 8.dp * 2
        val columnWidth = (screenWidth - spacing) / 3


        TaskColumnComposable(
            title = stringResource(R.string.to_do),
            tasks = allTasksByEveryoneWithImage.filter { it.task.progress == TaskStatus.TODO },
            backgroundColor = LightOcean,
            modifier = Modifier
                .width(columnWidth)
                .fillMaxHeight(),
            selectedStudentImage = selectedStudentImage
        )
        TaskColumnComposable(
            title = stringResource(R.string.in_progress),
            tasks = allTasksByEveryoneWithImage.filter { it.task.progress == TaskStatus.IN_PROGRESS },
            backgroundColor = Yellow,
            modifier = Modifier
                .width(columnWidth)
                .fillMaxHeight(),
            selectedStudentImage = selectedStudentImage
        )
        TaskColumnComposable(
            title = stringResource(R.string.done),
            tasks = allTasksByEveryoneWithImage.filter { it.task.progress == TaskStatus.DONE },
            backgroundColor = Green,
            modifier = Modifier
                .width(columnWidth)
                .fillMaxHeight(),
            selectedStudentImage = selectedStudentImage
        )
    }
}
