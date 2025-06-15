package com.example.edutasker.useCases.notification

import com.example.edutasker.model.NotificationsDetails
import com.example.edutasker.repo.notificationDatabase.INotificationDatabaseRepo

class GetUnreadNotificationsForStudentUseCase(
    private val repo: INotificationDatabaseRepo
) {
    suspend operator fun invoke(studentId: String): List<NotificationsDetails> {
        return repo.getUnreadNotificationsForStudent(studentId)
    }
}