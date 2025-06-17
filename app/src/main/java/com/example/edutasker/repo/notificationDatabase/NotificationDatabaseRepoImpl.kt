package com.example.edutasker.repo.notificationDatabase

import com.example.edutasker.db.dao.NotificationDao
import com.example.edutasker.mapper.toNotificationsDetails
import com.example.edutasker.model.NotificationsDetails

class NotificationDatabaseRepoImpl(
    private val notificationDao: NotificationDao,
) : INotificationDatabaseRepo {

    override suspend fun updateNotificationReadableByProfessor(
        notificationId: String,
        isRead: Boolean,
    ) {
        notificationDao.updateNotificationReadableByProfessor(notificationId, isRead)
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
}