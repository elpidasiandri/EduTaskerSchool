package com.example.edutasker.repo.notificationDatabase.common

import com.example.edutasker.db.entities.NotificationEntity
import kotlinx.coroutines.flow.Flow

interface INotificationCommonDatabaseRepo {
    suspend fun getUnreadCountForStudent(studentId: String): Flow<Int>
    suspend fun updateNotificationReadableByStudent(notificationId: String, isRead: Boolean)
    suspend fun updateNotificationReadableByProfessorForTask(taskId: String, isRead: Boolean)
    suspend fun updateNotificationReadableByStudentForTask(taskId: String, isRead: Boolean)
    suspend fun insertNotification(notification: NotificationEntity)
    suspend fun getUnreadCountForProfessor(professorId: String): Flow<Int>
}