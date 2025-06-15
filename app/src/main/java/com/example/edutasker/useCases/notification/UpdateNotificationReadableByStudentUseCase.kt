package com.example.edutasker.useCases.notification

import com.example.edutasker.repo.notificationDatabase.INotificationDatabaseRepo

class UpdateNotificationReadableByStudentUseCase(
    private val repo: INotificationDatabaseRepo
) {
    suspend operator fun invoke(notificationId: String, isRead: Boolean) {
        repo.updateNotificationReadableByStudent(notificationId, isRead)
    }
}