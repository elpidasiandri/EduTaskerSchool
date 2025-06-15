package com.example.edutasker.screens.student.viewModel.stateAndEvents

import com.example.edutasker.model.NotificationsDetails
import com.example.edutasker.model.OpenedTaskModel
import com.example.edutasker.model.ProfessorBasicModel
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.model.TaskModel

data class StudentState(
    val events: StudentEvents = StudentEvents.None,
    val uiEvents: StudentUiEvents = StudentUiEvents.None,
    val messageErrorId: Int = -1,
    val allTasks: List<TaskModel> = listOf(),
    val isTaskOpened: Boolean = false,
    val openedTask: OpenedTaskModel = OpenedTaskModel(
        TaskModel(), StudentPreviewAsListModel(),
        ProfessorBasicModel()
    ),
    val unreadNotificationsCount: Int = 0,
    val notifications: List<NotificationsDetails> = listOf(),
    val isNotificationDialogVisible: Boolean = false,
)