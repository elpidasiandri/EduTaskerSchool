package com.example.edutasker.repo.notificationDatabase

import com.example.edutasker.model.NotificationsDetails

interface INotificationDatabaseRepo {
    suspend fun updateNotificationReadableByProfessor(notificationId: String, isRead: Boolean)
    suspend fun getUnreadNotificationsForProfessor(professorId: String): List<NotificationsDetails> //TODO CHEKC
    suspend fun getUnreadNotificationsForStudent(studentId: String): List<NotificationsDetails>
    suspend fun getAllNotificationsForProfessor(professorId: String): List<NotificationsDetails>
    suspend fun getAllNotificationsForStudent(studentId: String): List<NotificationsDetails>
}