package com.example.edutasker.repo.notificationDatabase

import com.example.edutasker.db.entities.NotificationEntity
import com.example.edutasker.model.NotificationsDetails

interface INotificationDatabaseRepo {
    suspend fun insertNotification(notification: NotificationEntity)
    suspend fun getNotificationsDetails(): List<NotificationsDetails>
    suspend fun updateNotificationReadableByProfessor(notificationId: String, isRead: Boolean)
    suspend fun updateNotificationReadableByStudent(notificationId: String, isRead: Boolean)
    suspend fun updateNotificationReadableByProfessorForTask(taskId: String, isRead: Boolean)
    suspend fun updateNotificationReadableByStudentForTask(taskId: String, isRead: Boolean)
    suspend fun getUnreadNotificationsForProfessor(professorId: String): List<NotificationsDetails>
    suspend fun getUnreadNotificationsForStudent(studentId: String): List<NotificationsDetails>
    suspend fun getUnreadCountForProfessor(professorId: String): Int
    suspend fun getUnreadCountForStudent(studentId: String): Int
    suspend fun getAllNotificationsForProfessor(professorId: String): List<NotificationsDetails>
    suspend fun getAllNotificationsForStudent(studentId: String): List<NotificationsDetails>
}