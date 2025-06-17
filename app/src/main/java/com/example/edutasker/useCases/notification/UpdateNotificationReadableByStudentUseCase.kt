package com.example.edutasker.useCases.notification

import com.example.edutasker.repo.notificationDatabase.common.INotificationCommonDatabaseRepo

class UpdateNotificationReadableByStudentUseCase(
    private val repo: INotificationCommonDatabaseRepo,
) {
    suspend operator fun invoke(notificationId: String, isRead: Boolean) {
        repo.updateNotificationReadableByStudent(notificationId, isRead)
    }
}