package com.example.edutasker.repo.notificationDatabase

import com.example.edutasker.dao.NotificationDao
import com.example.edutasker.entities.NotificationEntity
import com.example.edutasker.mapper.toNotificationsDetails
import com.example.edutasker.model.NotificationsDetails

class NotificationDatabaseRepoImpl(
    private val notificationDao: NotificationDao
) : INotificationDatabaseRepo {
    override suspend fun insertNotification(notification: NotificationEntity) {
        if (!notificationDao.existsById(notification.notificationId)) {
            notificationDao.insertNotification(notification)
        }
    }

    override suspend fun getNotificationsDetails(): List<NotificationsDetails> {
        return notificationDao.getAllNotificationsWithDetails().map {
            it.toNotificationsDetails()
        }
    }
}