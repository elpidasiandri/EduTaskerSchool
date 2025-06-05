package com.example.edutasker.useCases.notification

import com.example.edutasker.db.entities.NotificationEntity
import com.example.edutasker.repo.notificationDatabase.INotificationDatabaseRepo

class InsertNotificationUseCase(
    private val repo: INotificationDatabaseRepo
) {
    suspend operator fun invoke(notification: NotificationEntity) {
        return repo.insertNotification(notification)
    }
}