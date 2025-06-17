package com.example.edutasker.useCases.notification

import com.example.edutasker.repo.notificationDatabase.common.INotificationCommonDatabaseRepo

class UpdateNotificationReadableByStudentForTaskUseCase(
    private val repo: INotificationCommonDatabaseRepo,
) {
    suspend operator fun invoke(taskId: String, isRead: Boolean) {
        repo.updateNotificationReadableByStudentForTask(taskId, isRead)
    }
}