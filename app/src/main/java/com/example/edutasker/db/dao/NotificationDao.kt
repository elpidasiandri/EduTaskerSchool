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
}