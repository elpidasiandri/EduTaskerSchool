package com.example.edutasker.model

data class NotificationsDetails(
    val notificationId: String,
    val taskDetails :TaskModel,
    val professorDetails :ProfessorBasicModel,
    val studentBasic: StudentPreviewAsListModel,
)
