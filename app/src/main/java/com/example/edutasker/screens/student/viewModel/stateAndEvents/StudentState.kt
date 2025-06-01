package com.example.edutasker.screens.student.viewModel.stateAndEvents

import com.example.edutasker.model.TaskModel

data class StudentState(
    val events: StudentEvents = StudentEvents.None,
    val uiEvents: StudentUiEvents = StudentUiEvents.None,
    val messageErrorId: Int = -1,
    val allTasks : List<TaskModel> = listOf(),
)

