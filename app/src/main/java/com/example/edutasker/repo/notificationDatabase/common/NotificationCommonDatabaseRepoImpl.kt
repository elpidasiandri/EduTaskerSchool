package com.example.edutasker.repo.notificationDatabase.common

import com.example.edutasker.db.dao.NotificationDao
import com.example.edutasker.db.entities.NotificationEntity
import kotlinx.coroutines.flow.Flow

class NotificationCommonDatabaseRepoImpl(
    private val notificationDao: NotificationDao,
) : INotificationCommonDatabaseRepo {
    override suspend fun getUnreadCountForStudent(studentId: String): Flow<Int> {
        return notificationDao.getUnreadCountForStudent(studentId)
    }

    override suspend fun updateNotificationReadableByProfessorForTask(
        taskId: String,
        isRead: Boolean,
    ) {
        notificationDao.updateNotificationReadableByProfessorForTask(taskId, isRead)
    }

    override suspend fun updateNotificationReadableByStudentForTask(
        taskId: String,
        isRead: Boolean,
    ) {
        notificationDao.updateNotificationReadableByStudentForTask(taskId, isRead)
    }

    override suspend fun insertNotification(notification: NotificationEntity) {
        val finalNotification = if (notification.notificationId.isEmpty()) {
            notification.copy(notificationId = getNewNotificationId())
        } else {
            notification
        }

        if (!notificationDao.existsById(finalNotification.notificationId)) {
            notificationDao.insertNotification(finalNotification)
        }
    }

    private suspend fun getNewNotificationId(): String {
        val lastId = notificationDao.getLastNotificationId()
        return if (lastId.isNullOrEmpty()) {
            "n1"
        } else {
            val number = lastId.removePrefix("n").toIntOrNull() ?: 0
            "n${number + 1}"
        }
    }

    override suspend fun getUnreadCountForProfessor(professorId: String): Flow<Int> {
        return notificationDao.getUnreadCountForProfessor(professorId)
    }
}