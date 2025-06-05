package com.example.edutasker.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "notifications"
)
data class NotificationEntity(
    @PrimaryKey val notificationId: String,
    val taskId: String,
    val studentId: String,
    val professorId: String,
    val readableByProfessor: Boolean,
    val readableByStudent: Boolean
)