package com.example.edutasker.repo.notificationDatabase

import com.example.edutasker.db.entities.NotificationEntity
import com.example.edutasker.model.NotificationsDetails

interface INotificationDatabaseRepo {
    suspend fun insertNotification(notification: NotificationEntity)
    suspend fun getNotificationsDetails() : List<NotificationsDetails>
}