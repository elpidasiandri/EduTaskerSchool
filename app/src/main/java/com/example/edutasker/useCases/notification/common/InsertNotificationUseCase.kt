package com.example.edutasker.useCases.notification.common

import com.example.edutasker.db.entities.NotificationEntity
import com.example.edutasker.repo.notificationDatabase.common.INotificationCommonDatabaseRepo

class InsertNotificationUseCase(
    private val repo: INotificationCommonDatabaseRepo,
) {
    suspend operator fun invoke(notification: NotificationEntity) {
        return repo.insertNotification(notification)
    }
}