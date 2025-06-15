package com.example.edutasker.screens.student.viewModel.stateAndEvents

import com.example.edutasker.model.UpdateTaskByProfessorModel

sealed class StudentEvents {
    data object None : StudentEvents()
    data object Logout : StudentEvents()
    data object CloseTaskDialog : StudentEvents()
    data class OpenTaskDialog(val taskId: String) : StudentEvents()
    data class UpdateTask(val taskInfo: UpdateTaskByProfessorModel) : StudentEvents()
    data object OpenNotificationDialog : StudentEvents()
    data object CloseNotificationDialog : StudentEvents()
    data class MarkNotificationAsRead(val notificationId: String) : StudentEvents()
    data object LoadNotifications : StudentEvents()
}

sealed class StudentUiEvents {
    data object GoToLogout : StudentUiEvents()
    data object None : StudentUiEvents()
    data object Success : StudentUiEvents()
    data object Error : StudentUiEvents()
}