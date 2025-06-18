package com.example.edutasker.useCases.notification.common

import com.example.edutasker.repo.notificationDatabase.common.INotificationCommonDatabaseRepo

class UpdateNotificationReadableByProfessorForTaskUseCase(
    private val repo: INotificationCommonDatabaseRepo,
) {
    suspend operator fun invoke(taskId: String, isRead: Boolean) {
        repo.updateNotificationReadableByProfessorForTask(taskId, isRead)
    }
}