package com.example.edutasker.repo.notificationDatabase.view

import com.example.edutasker.model.NotificationsDetails

interface INotificationDatabaseRepo {
    suspend fun updateNotificationReadableByProfessor(notificationId: String, isRead: Boolean)
    suspend fun getAllNotificationsForProfessor(professorId: String): List<NotificationsDetails>
    suspend fun getAllNotificationsForStudent(studentId: String): List<NotificationsDetails>
    suspend fun updateNotificationReadableByStudent(notificationId: String, isRead: Boolean)
}