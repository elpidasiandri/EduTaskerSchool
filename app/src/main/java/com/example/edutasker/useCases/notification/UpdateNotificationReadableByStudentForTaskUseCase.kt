package com.example.edutasker.useCases.notification

import com.example.edutasker.repo.notificationDatabase.INotificationDatabaseRepo

class UpdateNotificationReadableByStudentForTaskUseCase(
    private val repo: INotificationDatabaseRepo
) {
    suspend operator fun invoke(taskId: String, isRead: Boolean) {
        repo.updateNotificationReadableByStudentForTask(taskId, isRead)
    }
}