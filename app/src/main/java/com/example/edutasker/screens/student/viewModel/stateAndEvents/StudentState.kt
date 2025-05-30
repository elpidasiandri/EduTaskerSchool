package com.example.edutasker.screens.student.viewModel.stateAndEvents

import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.model.TasksWithStudentImageModel

data class StudentState(
    val events: StudentEvents = StudentEvents.None,
    val uiEvents: StudentUiEvents = StudentUiEvents.None,
    val messageErrorId: Int = -1,
    val studentsToAppearOnCentralRow: List<StudentPreviewAsListModel> = listOf(),
    val allTasksByEveryone: List<TasksWithStudentImageModel> = listOf(),
)

