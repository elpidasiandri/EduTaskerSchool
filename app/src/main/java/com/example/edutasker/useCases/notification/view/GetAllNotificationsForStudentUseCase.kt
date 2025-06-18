package com.example.edutasker.useCases.notification.view

import com.example.edutasker.model.NotificationsDetails
import com.example.edutasker.repo.notificationDatabase.view.INotificationDatabaseRepo

class GetAllNotificationsForStudentUseCase(
    private val repo: INotificationDatabaseRepo
) {
    suspend operator fun invoke(studentId: String): List<NotificationsDetails> {
        return repo.getAllNotificationsForStudent(studentId)
    }
}