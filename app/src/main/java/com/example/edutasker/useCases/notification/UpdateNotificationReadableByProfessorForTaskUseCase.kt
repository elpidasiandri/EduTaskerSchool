package com.example.edutasker.useCases.notification

import com.example.edutasker.repo.notificationDatabase.INotificationDatabaseRepo

class UpdateNotificationReadableByProfessorForTaskUseCase(
    private val repo: INotificationDatabaseRepo
) {
    suspend operator fun invoke(taskId: String, isRead: Boolean) {
        repo.updateNotificationReadableByProfessorForTask(taskId, isRead)
    }
}