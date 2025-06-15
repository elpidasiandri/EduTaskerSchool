package com.example.edutasker.useCases.notification

import com.example.edutasker.model.NotificationsDetails
import com.example.edutasker.repo.notificationDatabase.INotificationDatabaseRepo

class GetAllNotificationsForStudentUseCase(
    private val repo: INotificationDatabaseRepo
) {
    suspend operator fun invoke(studentId: String): List<NotificationsDetails> {
        return repo.getAllNotificationsForStudent(studentId)
    }
}