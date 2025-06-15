package com.example.edutasker.useCases.notification

import com.example.edutasker.repo.notificationDatabase.INotificationDatabaseRepo

class UpdateNotificationReadableByProfessorUseCase(
    private val repo: INotificationDatabaseRepo
) {
    suspend operator fun invoke(notificationId: String, isRead: Boolean) {
        repo.updateNotificationReadableByProfessor(notificationId, isRead)
    }
}