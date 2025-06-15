package com.example.edutasker.repo.notificationDatabase

import com.example.edutasker.db.dao.NotificationDao
import com.example.edutasker.db.entities.NotificationEntity
import com.example.edutasker.mapper.toNotificationsDetails
import com.example.edutasker.model.NotificationsDetails

class NotificationDatabaseRepoImpl(
    private val notificationDao: NotificationDao
) : INotificationDatabaseRepo {
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

    override suspend fun getNotificationsDetails(): List<NotificationsDetails> {
        return notificationDao.getAllNotificationsWithDetails().map {
            it.toNotificationsDetails()
        }
    }

    override suspend fun updateNotificationReadableByProfessor(notificationId: String, isRead: Boolean) {
        notificationDao.updateNotificationReadableByProfessor(notificationId, isRead)
    }

    override suspend fun updateNotificationReadableByStudent(notificationId: String, isRead: Boolean) {
        notificationDao.updateNotificationReadableByStudent(notificationId, isRead)
    }

    override suspend fun updateNotificationReadableByProfessorForTask(taskId: String, isRead: Boolean) {
        notificationDao.updateNotificationReadableByProfessorForTask(taskId, isRead)
    }

    override suspend fun updateNotificationReadableByStudentForTask(taskId: String, isRead: Boolean) {
        notificationDao.updateNotificationReadableByStudentForTask(taskId, isRead)
    }

    override suspend fun getUnreadNotificationsForProfessor(professorId: String): List<NotificationsDetails> {
        return notificationDao.getUnreadNotificationsForProfessor(professorId).map {
            it.toNotificationsDetails()
        }
    }

    override suspend fun getUnreadNotificationsForStudent(studentId: String): List<NotificationsDetails> {
        return notificationDao.getUnreadNotificationsForStudent(studentId).map {
            it.toNotificationsDetails()
        }
    }

    override suspend fun getUnreadCountForProfessor(professorId: String): Int {
        return notificationDao.getUnreadCountForProfessor(professorId)
    }

    override suspend fun getUnreadCountForStudent(studentId: String): Int {
        return notificationDao.getUnreadCountForStudent(studentId)
    }

    override suspend fun getAllNotificationsForProfessor(professorId: String): List<NotificationsDetails> {
        return notificationDao.getAllNotificationsForProfessor(professorId).map {
            it.toNotificationsDetails()
        }
    }

    override suspend fun getAllNotificationsForStudent(studentId: String): List<NotificationsDetails> {
        return notificationDao.getAllNotificationsForStudent(studentId).map {
            it.toNotificationsDetails()
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
}