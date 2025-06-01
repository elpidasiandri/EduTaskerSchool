package com.example.edutasker.composable.student.menu

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.edutasker.R
import com.example.edutasker.screens.student.viewModel.stateAndEvents.StudentEvents
import com.example.edutasker.utils.noRippleClickable
import kotlinx.coroutines.launch

@Composable
fun MenuStudentComposable(onEvent: (StudentEvents) -> Unit, scaffoldState: ScaffoldState) {
    val interactionSource = remember { MutableInteractionSource() }
    val coroutineScope = rememberCoroutineScope()

    Column {
        Text(
            stringResource(R.string.dashboard),
            modifier = Modifier
                .padding(16.dp)
                .noRippleClickable(
                    interactionSource = interactionSource,
                    onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                ))
        Text(stringResource(R.string.notifications), modifier = Modifier.padding(16.dp))
    }
}