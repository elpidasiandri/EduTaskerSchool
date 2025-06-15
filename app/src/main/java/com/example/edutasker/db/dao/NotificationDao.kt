package com.example.edutasker.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.edutasker.db.entities.NotificationEntity
import com.example.edutasker.db.entities.relations.NotificationWithDetails

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: NotificationEntity)

    @Query ("SELECT EXISTS(SELECT 1 FROM notifications WHERE notificationId = :notificationId)")
    suspend fun existsById(notificationId: String): Boolean

    @Transaction
    @Query("SELECT * FROM notifications WHERE notificationId = :notificationId")
    suspend fun getNotificationWithDetails(notificationId: String): NotificationWithDetails?

    @Transaction
    @Query("SELECT * FROM notifications")
    suspend fun getAllNotificationsWithDetails(): List<NotificationWithDetails>

    @Query(
        """
    SELECT notificationId FROM notifications 
    WHERE notificationId LIKE 'n%' 
    ORDER BY CAST(SUBSTR(notificationId, 2) AS INTEGER) DESC 
    LIMIT 1
"""
    )
    suspend fun getLastNotificationId(): String?

    @Query(
        """
        UPDATE notifications 
        SET readableByProfessor = :isRead
        WHERE notificationId = :notificationId
    """
    )
    suspend fun updateNotificationReadableByProfessor(notificationId: String, isRead: Boolean)

    @Query(
        """
        UPDATE notifications 
        SET readableByStudent = :isRead
        WHERE notificationId = :notificationId
    """
    )
    suspend fun updateNotificationReadableByStudent(notificationId: String, isRead: Boolean)

    @Query(
        """
        UPDATE notifications 
        SET readableByProfessor = :isRead
        WHERE taskId = :taskId
    """
    )
    suspend fun updateNotificationReadableByProfessorForTask(taskId: String, isRead: Boolean)

    @Query(
        """
        UPDATE notifications 
        SET readableByStudent = :isRead
        WHERE taskId = :taskId
    """
    )
    suspend fun updateNotificationReadableByStudentForTask(taskId: String, isRead: Boolean)

    @Transaction
    @Query(
        """
        SELECT * FROM notifications 
        WHERE professorId = :professorId 
        AND readableByProfessor = 0
        ORDER BY notificationId DESC
    """
    )
    suspend fun getUnreadNotificationsForProfessor(professorId: String): List<NotificationWithDetails>

    @Transaction
    @Query(
        """
        SELECT * FROM notifications 
        WHERE studentId = :studentId 
        AND readableByStudent = 0
        ORDER BY notificationId DESC
    """
    )
    suspend fun getUnreadNotificationsForStudent(studentId: String): List<NotificationWithDetails>

    @Query(
        """
        SELECT COUNT(*) FROM notifications 
        WHERE professorId = :professorId 
        AND readableByProfessor = 0
    """
    )
    suspend fun getUnreadCountForProfessor(professorId: String): Int

    @Query(
        """
        SELECT COUNT(*) FROM notifications 
        WHERE studentId = :studentId 
        AND readableByStudent = 0
    """
    )
    suspend fun getUnreadCountForStudent(studentId: String): Int

    @Transaction
    @Query(
        """
        SELECT * FROM notifications 
        WHERE professorId = :professorId 
        ORDER BY notificationId DESC
    """
    )
    suspend fun getAllNotificationsForProfessor(professorId: String): List<NotificationWithDetails>

    @Transaction
    @Query(
        """
        SELECT * FROM notifications 
        WHERE studentId = :studentId 
        ORDER BY notificationId DESC
    """
    )
    suspend fun getAllNotificationsForStudent(studentId: String): List<NotificationWithDetails>
}